package com.astar.order_admin.data.request.member;

import lombok.Data;

@Data
public class PwdModifyRequestVO {
    private Integer memberSeq;
    private String newPwd;
    private String originPwd;
}
