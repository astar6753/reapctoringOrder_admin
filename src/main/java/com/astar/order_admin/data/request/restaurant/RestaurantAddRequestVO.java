package com.astar.order_admin.data.request.restaurant;

import lombok.Data;

@Data
public class RestaurantAddRequestVO {
    private Integer restSeq;
    private Integer meberSeq;
    private String restName;
    private Integer restMinPrice;
    private Integer restDeliveryFee;
    private String restAddress;
    private Integer restCateSeq;
    private String restOpenTime;
    private String restEndTime;
    private String restDescription;
}