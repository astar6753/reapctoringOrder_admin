package com.astar.order_admin.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.astar.order_admin.service.ImgService;

@RestController
@RequestMapping("/api/img")
public class ImgAPIController {
    @Autowired ImgService imgService;
    
    //이미지 파일 불러오기
    @GetMapping("/{type}/{filename}")
    public ResponseEntity<Resource> getImage(
        @PathVariable String type, @PathVariable @Nullable String filename, HttpServletRequest request) {
            String contentType = (String)imgService.ImgFile(type,filename,request).get("contentType");
            Resource resource = (Resource)imgService.ImgFile(type,filename,request).get("resource");
        return
            ResponseEntity.ok() //결과로 200ok를 설정
            //파일의 타입을 Spring프레임 워크를 통해 파일 유형을 결정
            .contentType(MediaType.parseMediaType(contentType))
            //파일 이름의 표시방법을 설정
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\""+resource.getFilename()+"\"")
            //실제 리소스uri를 body에 포함
            .body(resource);
    }

    //세션의 로그인한 유저가 본인이 등록한 이미지 조회
    @GetMapping("/user/{type}")
    public ResponseEntity<Map<String,Object>> getImgList(
        HttpSession session, @PathVariable String type, @RequestParam @Nullable Integer offset
        ){
        return new ResponseEntity<Map<String,Object>>(imgService.ImgList(session,type,offset),HttpStatus.OK);
    }

    //유저 이미지보관함 업로드 및 디비 등록
    @PutMapping("/{type}")
    public ResponseEntity<Map<String,Object>> putImgInfo(
        HttpSession session, @PathVariable String type, @RequestPart MultipartFile file
        ){
        return new ResponseEntity<Map<String,Object>>(imgService.ImgInfo(session,type,file),HttpStatus.OK);
    }
    
    //이미지 파일삭제 및 디비삭제
    @DeleteMapping("/{type}")
    public ResponseEntity<Map<String,Object>> deleteImgInfo(
        @PathVariable String type, @RequestParam Integer imgSeq
        ){
        return new ResponseEntity<Map<String,Object>>(imgService.deleteImgInfoByImgSeq(type,imgSeq),HttpStatus.OK);
    }

    //영업장/음식/유저 테이블에 등록된 이미지 변경
    @PatchMapping("/{type}")
    public ResponseEntity<Map<String,Object>> updateImgInfo(
        @PathVariable String type, @RequestParam Integer imgSeq, @RequestParam Integer seq
        ){
        return new ResponseEntity<Map<String,Object>>(imgService.updateImgToInfoByType(type,imgSeq,seq),HttpStatus.OK);
    }
    



    
}
