package com.astar.order_admin.data.request.member;

import lombok.Data;

@Data
public class MemberRequestVO {
    private Integer memberSeq;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberPhone;
    private String memberAddress;
    private Integer memberStatus;
    private Integer memberGrade;
}
