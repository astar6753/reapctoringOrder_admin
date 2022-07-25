package com.astar.order_admin.data.response.img;

import lombok.Data;

@Data
public class ImgResponseVO {
    private Integer imgSeq;
    private Integer memberSeq;
    private Integer imgTypeNo;
    private String imgFrontName;
    private String imgBackName;    
}
