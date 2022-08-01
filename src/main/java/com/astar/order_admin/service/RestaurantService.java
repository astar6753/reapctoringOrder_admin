package com.astar.order_admin.service;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.astar.order_admin.data.request.restaurant.RestaurantAddRequestVO;
import com.astar.order_admin.data.request.restaurant.RestaurantUpdateRequestVO;
import com.astar.order_admin.data.response.member.MemberResponseVO;
import com.astar.order_admin.mapper.RestaurantMapper;

@Service
public class RestaurantService {
    @Autowired RestaurantMapper restMapper;
    @Autowired UserService userService;
    

    //사업자회원 영업장 추가
    public Map<String,Object> addRestaurant(RestaurantAddRequestVO data, HttpSession session) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        
        //유저로그인/사업자회원여부 검사
        if(!userService.isValidUser(session)){
            resultMap.put("status", false);
            resultMap.put("message", "비정상적인 접근입니다.");    
            return resultMap;
        }
        
        // //현재 세션유저저보 추가
        Integer userSeq = ((MemberResponseVO)session.getAttribute("user")).getMemberSeq();
        data.setMemberSeq(userSeq);
        
        
        //상호명 중복 검사
        try {
            restMapper.insertRestaurantInfo(data);
        } catch (DuplicateKeyException e) {
            resultMap.put("status",false);
            resultMap.put("message",data.getRestName()+"은(는) 이미 등록된 상호 명입니다.");
            return resultMap;
        }
        
        resultMap.put("status" ,true);
        resultMap.put("message", "영업장이 정상적으로 등록되었습니다.");    
        return resultMap;
    }
    
    //사업자회원 영엄장 조회
    public Map<String,Object> userRestaurant(Integer page, HttpSession session, String keyword) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        
        //유저로그인/사업자회원여부 검사
        if(!userService.isValidUser(session)){
            resultMap.put("status",false);
            resultMap.put("message", "비정상적인 접근입니다.");
            return resultMap;
        }
        
        //현재 로그인한 회원의 번호로 영업장 조회 20개씩 1페이지
        Integer userSeq = ((MemberResponseVO)session.getAttribute("user")).getMemberSeq();
        if(page==null) page=1;
        resultMap.put("keyword", keyword);  //검색어 유지를 위해 키워드 반환
        resultMap.put("currentPage", page); 
        resultMap.put("restList", restMapper.selectRestaurantViewByUser(userSeq, (page-1)*10, keyword));
        resultMap.put("totalCnt", restMapper.selectRestaurantViewTotalCntByUser(userSeq, keyword));
        resultMap.put("totalPage", restMapper.selectRestaurantViewPageByUser(userSeq, keyword));
        resultMap.put("message", "현재 로그인한 사업자 회원의 영업장 정보입니다.");
        return resultMap;
    }

    //사업자회원 영업장 정보 수정
    public Map<String,Object> modifyRestaurant(RestaurantUpdateRequestVO data, HttpSession session) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();

        // 유저로그인/사업자회원여부 검사
        if(!userService.isValidUser(session)){
            resultMap.put("status",false);
            resultMap.put("message","비정상적인 접근입니다.");    
            return resultMap;
        }
                
        Integer userSeq = ((MemberResponseVO)session.getAttribute("user")).getMemberSeq();
        data.setMemberSeq(userSeq);
        
        restMapper.updateRestaurantInfo(data);
        resultMap.put("status", true);
        resultMap.put("message", "선택한 영업장 정보가 수정되었습니다.");

        return resultMap;
    }
    
    //사업자회원 영엄장 삭제
    public Map<String,Object> deleteRestaurant(Integer restSeq, HttpSession session) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();

        //유저로그인/사업자회원여부 검사
        if(!userService.isValidUser(session)){
            resultMap.put("status",false);
            resultMap.put("message","비정상적인 접근입니다.");    
            return resultMap;
        }

        Integer userSeq = ((MemberResponseVO)session.getAttribute("user")).getMemberSeq();
        restMapper.deleteRestaurantInfoBySeq(restSeq, userSeq);
        resultMap.put("status", true);
        resultMap.put("message", "선택한 영업장 정보가 삭제되었습니다.");

        return resultMap;
    }
    
    //카테고리 검색결과 근사치 10개
    public Map<String,Object> searchCategory(String keyword) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();

        resultMap.put("status", true);
        resultMap.put("searchResult",restMapper.selectCategoryList(keyword));
        resultMap.put("keyword", keyword);
        resultMap.put("message", "카테고리 검색결과입니다.");

        return resultMap;
    }




}
