package com.astar.order_admin.data.request.restaurant;

import lombok.Data;

@Data
public class RestaurantUpdateRequestVO {
    private Integer restSeq;
    private Integer memberSeq;
    private Integer cateSeq;
    private String restName;
    private Integer restMinPrice;
    private Integer restDeliveryFee;
    private String restAddress;
    private String restOpenTime;
    private String restEndTime;
    private String restDescription;
}