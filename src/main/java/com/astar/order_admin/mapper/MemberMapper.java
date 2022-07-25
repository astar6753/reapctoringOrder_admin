package com.astar.order_admin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.astar.order_admin.data.request.member.LoginRequestVO;
import com.astar.order_admin.data.request.member.MemberRequestVO;
import com.astar.order_admin.data.request.member.PwdModifyRequestVO;
import com.astar.order_admin.data.response.member.MemberResponseVO;

@Mapper
public interface MemberMapper {
    
    public void insertMemberInfo(MemberRequestVO data);
    public MemberResponseVO selectMemberLogin(LoginRequestVO data);
    public boolean isDuplicatedId(String id);
    public MemberResponseVO selectMemberInfoBySeq(Integer seq);
    public void updateMemberInfo(MemberRequestVO data);
    public void updateMemberPwd(PwdModifyRequestVO data);
}
