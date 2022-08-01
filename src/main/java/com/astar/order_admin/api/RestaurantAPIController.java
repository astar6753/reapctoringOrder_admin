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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.order_admin.data.request.restaurant.RestaurantAddRequestVO;
import com.astar.order_admin.data.request.restaurant.RestaurantUpdateRequestVO;
import com.astar.order_admin.service.RestaurantService;


@RestController
@RequestMapping("/api/restaurant")
public class RestaurantAPIController {
    @Autowired RestaurantService restService;

    //사업자회원 영업장 추가
    @PutMapping("/insert")
    public ResponseEntity<Map<String,Object>> putRestaurantInfo(
        @RequestBody RestaurantAddRequestVO data, HttpSession session
        ){
        return new ResponseEntity<Map<String,Object>>(restService.addRestaurant(data, session),HttpStatus.OK);
    }

    //사업자회원 영엄장 조회
    @GetMapping("/list") 
    public ResponseEntity<Map<String,Object>> getUserRestaurant(
        @RequestParam @Nullable Integer page, HttpSession session, @RequestParam @Nullable String keyword
        ) {       
        return new ResponseEntity<Map<String,Object>>(restService.userRestaurant(page, session, keyword),HttpStatus.OK);
    }
    
    //회원 자신의 영업장 정보 수정
    @PatchMapping("/update") 
    public ResponseEntity<Map<String,Object>> updateUserRestaurant(
        @RequestBody RestaurantUpdateRequestVO data, HttpSession session
        ) {
        return new ResponseEntity<Map<String,Object>>(restService.modifyRestaurant(data, session),HttpStatus.OK);
    }

    //사업자회원 영엄장 삭제
    @DeleteMapping("/delete") 
    public ResponseEntity<Map<String,Object>> deleteUserRestaurant(
        @RequestParam Integer restSeq, HttpSession session
        ) {
        return new ResponseEntity<Map<String,Object>>(restService.deleteRestaurant(restSeq, session),HttpStatus.OK);
    }

    //카테고리 검색결과 근사치 최대 10개
    @GetMapping("/category")
    public ResponseEntity<Map<String,Object>> getCategorySearchList(
        @RequestParam String keyword
        ){
        return new ResponseEntity<Map<String,Object>>(restService.searchCategory(keyword),HttpStatus.OK);
    }    
}
