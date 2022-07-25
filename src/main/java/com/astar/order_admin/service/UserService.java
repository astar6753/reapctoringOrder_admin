package com.astar.order_admin.service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.astar.order_admin.data.response.member.MemberResponseVO;

@Service
public class UserService {
    
    //세션에 유저정보 가져와서 null=로그인X memberGrade=1 일반회원
    public Boolean isValidUser(HttpSession session){
        MemberResponseVO user = (MemberResponseVO)session.getAttribute("user");
        if(user==null) return false;
        if(user.getMemberGrade()==1) return false;
        return true;
    }

}
