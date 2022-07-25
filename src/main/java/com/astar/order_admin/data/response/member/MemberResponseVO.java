package com.astar.order_admin.data.response.member;

import lombok.Data;

@Data
public class MemberResponseVO {
    private Integer memberSeq;
    private String memberId;
    private String memberName;
    private String memberPhone;
    private Integer memberStatus;
    private Integer memberGrade;
    private String memberAddress;
    private Integer imgSeq;
}
