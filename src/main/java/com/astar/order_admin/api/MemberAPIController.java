package com.astar.order_admin.api;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.order_admin.data.request.member.LoginRequestVO;
import com.astar.order_admin.data.request.member.MemberRequestVO;
import com.astar.order_admin.data.request.member.PwdModifyRequestVO;
import com.astar.order_admin.service.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberAPIController {
    @Autowired MemberService memberService;

    //회원가입
    @PutMapping("/join")
    public ResponseEntity<Map<String,Object>> putMemberJoin(
        @RequestBody MemberRequestVO data, HttpSession session) throws Exception {
        return new ResponseEntity<Map<String,Object>>(memberService.memberJoin(data, session),HttpStatus.OK);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> postAdminLogin(
        @RequestBody LoginRequestVO data, HttpSession session) throws Exception {
        return new ResponseEntity<Map<String,Object>>(memberService.AdminLogin(data, session),HttpStatus.OK);
    }

    //아이디 중복검사 클릭시
    @GetMapping("/chk")
    public ResponseEntity<Map<String,Object>> getMemberJoinIdChk(@RequestParam String id) {
        return new ResponseEntity<Map<String,Object>>(memberService.duplicatedIdChk(id),HttpStatus.OK);
    }

    //회원정보조회
    @GetMapping("/info")
    public ResponseEntity<Map<String,Object>> getMemberInfo(HttpSession session) {
        return new ResponseEntity<Map<String,Object>>(memberService.memberInfo(session),HttpStatus.OK);
    }

    //회원정보수정
    @PatchMapping("/info/update")
    public ResponseEntity<Map<String,Object>> patchMemberInfo(@RequestBody MemberRequestVO data, HttpSession session) throws Exception {
        return new ResponseEntity<Map<String,Object>>(memberService.modifyMemberInfo(data,session),HttpStatus.OK);
    }

    //비밀번호변경
    @PatchMapping("/pwd/update")
    public ResponseEntity<Map<String,Object>> patchMemberPwd(@RequestBody PwdModifyRequestVO data, HttpSession session) throws Exception {
        return new ResponseEntity<Map<String,Object>>(memberService.modifyMemberPwd(data,session),HttpStatus.OK);
    }
    
}
