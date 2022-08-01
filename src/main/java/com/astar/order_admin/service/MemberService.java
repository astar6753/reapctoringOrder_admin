package com.astar.order_admin.service;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.astar.order_admin.data.request.member.LoginRequestVO;
import com.astar.order_admin.data.request.member.MemberRequestVO;
import com.astar.order_admin.data.request.member.PwdModifyRequestVO;
import com.astar.order_admin.data.response.member.MemberResponseVO;
import com.astar.order_admin.mapper.MemberMapper;
import com.astar.order_admin.utils.AESAlgorithm;

@Service
public class MemberService {
    @Autowired MemberMapper memberMapper;

    //회원가입
    public Map<String,Object> memberJoin(MemberRequestVO data, HttpSession session)  throws Exception {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        data.setMemberPwd(AESAlgorithm.Encrypt(data.getMemberPwd()));
        
        try {
            memberMapper.insertMemberInfo(data);
        } catch (DuplicateKeyException e) {         //아이디PK키 중복시 catch
            resultMap.put("status",false);
            resultMap.put("message",data.getMemberId()+"은(는) 이미 등록된 아이디입니다.");
            return resultMap;
        }        
        
        resultMap.put("status",true);
        resultMap.put("message","회원 정보를 추가했습니다.");
        //반환된 memberSeq값으로 방금 회원가입한 유저정보를 조회해서 세션에 추가
        MemberResponseVO user = memberMapper.selectMemberInfoBySeq(data.getMemberSeq());
        session.setAttribute("user",user);
        
        return resultMap;
    }
    
    //로그인
    public Map<String,Object> adminLogin(LoginRequestVO data, HttpSession session)  throws Exception {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
         //사용자로부터 입력받은 비밀번호 정보를 암호화하고 db에서 일치정보 꺼내오기
        data.setLoginPwd(AESAlgorithm.Encrypt(data.getLoginPwd()));
        MemberResponseVO user = memberMapper.selectMemberLogin(data);
        //사용자로부터 입력받은 id pwd로부터 회원정보가 null일 경우(일치값없음) = 아이디/비밀번호 오류
        if(user==null){
            resultMap.put("status", false);
            resultMap.put("message", "아이디 혹은 비밀번호 오류입니다.");
            return resultMap;
        }
        else if(user.getMemberGrade()>2){
            resultMap.put("status", false);
            resultMap.put("message", "사업자 회원만 이용가능한 서비스입니다.");
            return resultMap;
        }
        resultMap.put("status", true);
        resultMap.put("message", user.getMemberName()+"님 방문을 환영합니다.");
        //로그인성공시 세션에 user정보 세팅
        session.setAttribute("user",user);
        return resultMap;
    }

    //아이디 중복검사
    public Map<String,Object> duplicatedIdChk(String id) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        if(memberMapper.isDuplicatedId(id)){
            resultMap.put("status", false);
            resultMap.put("message", "이미 사용중인 ID입니다.");
            return resultMap;
        }
        resultMap.put("status", true);
        resultMap.put("message", "사용가능한 ID입니다.");
        return resultMap;
    }

    //세션정보로부터 회원정보조회
    public Map<String,Object> memberInfo(HttpSession session) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        MemberResponseVO user = (MemberResponseVO)session.getAttribute("user");
        
        //세션의 유저정보가 null일 경우 status false리턴
        if(user==null){
            resultMap.put("status", false);
            resultMap.put("message", "정상적인 접근 경로가 아닙니다.");
            return resultMap;
        }        
        resultMap.put("status",true);
        resultMap.put("message", "현재 로그인한 회원의 회원정보입니다.");
        resultMap.put("memberInfo",memberMapper.selectMemberInfoBySeq(user.getMemberSeq()));
        return resultMap;
    }

    //회원정보수정(세션memberSeq/사용자입력data)
    public Map<String,Object> modifyMemberInfo(MemberRequestVO data, HttpSession session) throws Exception {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        MemberResponseVO user = (MemberResponseVO)session.getAttribute("user");
        
        //user==null 현재 로그인한 세션 유저정보가 없음
        if(user==null){
            resultMap.put("status", false);
            resultMap.put("message", "정상적인 접근 경로가 아닙니다.");
            return resultMap;
        }
        data.setMemberSeq(user.getMemberSeq());
        
        //사용자로부터 받은 데이터에서 pwd를 암호화
        data.setMemberPwd(AESAlgorithm.Encrypt(data.getMemberPwd()));
        
        memberMapper.updateMemberInfo(data);
        //세션의 유저정보도 동시에 업데이트
        session.setAttribute("user",user);
        resultMap.put("status",true);
        resultMap.put("message","회원 정보를 수정했습니다.");

        return resultMap;
    }
    
    //비밀번호변경
    public Map<String,Object> modifyMemberPwd(PwdModifyRequestVO data, HttpSession session) throws Exception {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        MemberResponseVO user = (MemberResponseVO)session.getAttribute("user");
        
        //user==null 현재 로그인한 세션 유저정보가 없음
        if(user==null){
            resultMap.put("status", false);
            resultMap.put("message", "정상적인 접근 경로가 아닙니다.");
            return resultMap;
        }
        data.setMemberSeq(user.getMemberSeq());
        

        //사용자로부터 받은 데이터에서 pwd를 암호화
        data.setOriginPwd(AESAlgorithm.Encrypt(data.getOriginPwd()));
        data.setNewPwd(AESAlgorithm.Encrypt(data.getNewPwd()));
        
        memberMapper.updateMemberPwd(data);
        //(old)비밀번호수정은 변하는 세션정보없음
        //세션의 유저정보도 동시에 업데이트
        //session.setAttribute("user",user);
        resultMap.put("status",true);
        resultMap.put("message","회원 정보를 수정했습니다.");

        return resultMap;
    }
}