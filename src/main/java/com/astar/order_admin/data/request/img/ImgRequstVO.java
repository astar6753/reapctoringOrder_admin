package com.astar.order_admin.data.request.img;

import lombok.Data;

@Data
public class ImgRequstVO {
    private Integer imgSeq;
    private Integer memberSeq;
    private Integer imgTypeNo;
    private String imgFrontName;
    private String imgBackName;
}
