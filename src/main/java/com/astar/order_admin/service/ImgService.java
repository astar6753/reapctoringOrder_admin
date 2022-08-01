package com.astar.order_admin.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.astar.order_admin.data.request.img.ImgRequstVO;
import com.astar.order_admin.data.response.img.ImgResponseVO;
import com.astar.order_admin.data.response.member.MemberResponseVO;
import com.astar.order_admin.mapper.ImgMapper;

@Service
public class ImgService {
    @Value("${spring.servlet.multipart.location}") String path;
    @Autowired ImgMapper imgMapper;
    
    public Integer imgTypeToNumber(String type){
        Integer typeNo = 0;
        if(type.equals("restaurant")) typeNo = 1;
        else if(type.equals("dish")) typeNo = 2;
        else if(type.equals("member")) typeNo = 3;
        return typeNo;
    }

    //이미지 파일 불러오기
    public Map<String,Object> ImgFile(String type, String filename, HttpServletRequest request){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        
        Path folderLocation = Paths.get(path+"/"+type);     // folderLocation = /path/{type}
        Path filePath = folderLocation.resolve(filename);   // filePath = path/type/filename = d:/order/member/default.jpg
        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (Exception e) {
            resultMap.put("status", false);
            resultMap.put("message","파일을 찾을 수 없거나 잘못된 파일 경로 입니다.");
        }
        //파일의 실제 경로에 찾아가서 파일의 유형을 가져온다.
        String contentType = null;
        try {
            request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());            
            if(contentType == null)
            contentType = "application/octet-stream";
        } catch (Exception e) {
            resultMap.put("status", false);
            resultMap.put("message","파일을 찾을 수 없거나 잘못된 파일 경로 입니다.");
        }
        resultMap.put("contentType", contentType);
        resultMap.put("resource", resource);
        return resultMap;
    }

    //세션의 로그인한 유저가 본인이 등록한 이미지 조회
    public Map<String,Object> ImgList(HttpSession session, String type, Integer offset){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        
        Integer memberSeq = ((MemberResponseVO)session.getAttribute("user")).getMemberSeq();
        Integer typeNo = imgTypeToNumber(type);
        if(offset==null||offset<0) offset =0;
        
        List<ImgResponseVO> imgList = imgMapper.selectImgInfoBySeq(memberSeq, typeNo, offset);

        resultMap.put("status", true);
        resultMap.put("imgList", imgList);
        return resultMap;
    }

    //유저 이미지보관함 파일업로드 및 디비등록
    public Map<String,Object> ImgInfo(HttpSession session, String type, MultipartFile file){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        
        //업로드 할 파일 경로 생성 /order/{type}
        Path folderLocation = Paths.get(path+"/"+type);
        String fileName = file.getOriginalFilename();
        String[] fileNameSplit = fileName.split("\\.");
        String imgFrontName = fileNameSplit[0];
        String ext = fileNameSplit[fileNameSplit.length-1];
        
        if(!ext.equalsIgnoreCase("gif") && !ext.equalsIgnoreCase("jpg") && !ext.equalsIgnoreCase("png")){
            resultMap.put("status", false);
            resultMap.put("message","이미지 파일 확장자는 jpg, png, gif만 허용합니다.");
            return resultMap;
        }

        Calendar c = Calendar.getInstance();
        //파일 저장시 "타입_타입스탬프.ext"형식으로 저장
        String imgBackName = StringUtils.cleanPath(type+"_"+c.getTimeInMillis()+"."+ext);
        Path target = folderLocation.resolve(imgBackName);

        try {
            //파일업로드    //option 이미 존재하면 덮어쓰기
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            //파일 업로드 실패
            resultMap.put("status",false);
            resultMap.put("message",e.getMessage());
            return resultMap;
        }

        Integer memberSeq = ((MemberResponseVO)session.getAttribute("user")).getMemberSeq();
        Integer typeNo = imgTypeToNumber(type);
        long fileSize = file.getSize();

        //db에 insert
        ImgRequstVO data = new ImgRequstVO();
        data.setMemberSeq(memberSeq);
        data.setImgTypeNo(typeNo);
        data.setImgFrontName(imgFrontName);
        data.setImgBackName(imgBackName);
        imgMapper.insertImgInfo(data);

        resultMap.put("status",true);
        resultMap.put("message","파일 업로드 완료");
        resultMap.put("imgFrontName", imgFrontName);
        resultMap.put("imgBackName", imgBackName);
        resultMap.put("ext", ext);
        resultMap.put("fileSize", fileSize);
        return resultMap;
    }
    
    //이미지 파일삭제 및 디비삭제
    public Map<String,Object> deleteImgInfoByImgSeq(String type, Integer imgSeq){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();

        //이미지seq번호로 imgBackName조회
        String filename = imgMapper.selectImgBackNameByImgSeq(imgSeq);

        String filepath = path+"/"+type+"/"+filename;
        File deleteFile = new File(filepath);

        if(deleteFile.exists()) {
            //파일삭제
            deleteFile.delete();
        }
        else {
            resultMap.put("status",false);
            resultMap.put("message","파일이 존재하지 않습니다.");
            resultMap.put("path",filepath);
            return resultMap;
        }
        //db정보 삭제
        imgMapper.deleteImgInfoByImgSeq(imgSeq);

        resultMap.put("status",true);
        resultMap.put("message","파일을 삭제했습니다.");
        resultMap.put("path",filepath);
        return resultMap;
    }

    //이미지 디비 수정
    public Map<String,Object> updateImgToInfoByType(String type, Integer imgSeq, Integer seq){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        Integer type_no = imgTypeToNumber(type);
        if(type_no==0) {
            resultMap.put("status", false);
            resultMap.put("message", "맞지 않는 타입입니다.");
            return resultMap;
        }
        else if(type_no==1) {
            Integer restSeq = seq;
            imgMapper.updateRestaurantImg(imgSeq,restSeq);
        }
        else if(type_no==2) {
            Integer dishSeq = seq;
            imgMapper.updateDishImg(imgSeq,dishSeq);
        
        }
        else if(type_no==3) {
            Integer memberSeq = seq;
            imgMapper.updateMemberImg(imgSeq,memberSeq);
        }
        resultMap.put("status", true);
        resultMap.put("message", "이미지가 등록되었습니다.");
        return resultMap;
    }

}
