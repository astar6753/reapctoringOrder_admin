package com.astar.order_admin.data.request.order;

import java.util.Date;

import lombok.Data;

@Data
public class OrderRequestVO {
    private Integer oi_mi_seq;
    private Integer oi_ri_seq ;
    private Integer oi_status ;
    private String oi_address;
    private Date oi_reg_dt;
}
