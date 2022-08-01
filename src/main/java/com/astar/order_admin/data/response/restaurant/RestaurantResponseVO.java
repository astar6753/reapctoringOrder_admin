package com.astar.order_admin.data.response.restaurant;

import lombok.Data;

@Data
public class RestaurantResponseVO {
    private Integer restSeq;
    private Integer memberSeq;
    private String restName;
    private Integer restMinPrice;
    private Integer restDeliveryFee;
    private String restAddress;
    private String restOpenTime;
    private String restEndTime;
    private String restDescription;
    private Integer cateSeq;
    private String cateName;   //cate_seq로 category_info테이블에서 cate_name
    private Integer restImgSeq;
    private String restImgFile; //img_seq로 img_info테이블에서 img_back_name
}
