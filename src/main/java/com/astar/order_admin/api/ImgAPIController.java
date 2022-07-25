package com.astar.order_admin.api;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/user/img")
public class ImgAPIController {
    @Autowired ImgService imgService;

    //세션의 로그인한 유저가 본인이 등록한 이미지 조회
    @GetMapping("/{type}/{fileName}")
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

    //이미지보관함의 이미지를 영업장/음식/유저 테이블 등록
    @PatchMapping("/{type}")
    public ResponseEntity<Map<String,Object>> updateImgInfo(
        @PathVariable String type, @RequestParam Integer imgSeq, @RequestParam Integer seq
        ){
        return new ResponseEntity<Map<String,Object>>(imgService.updateImgToInfoByType(type,imgSeq,seq),HttpStatus.OK);
    }
    



    
}
