package com.astar.order_admin.data.response.restaurant;

import lombok.Data;

@Data
public class RestaurantViewResponse {
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
    private String cateName;
    private Integer imgSeq;
    private Integer imgFile;
}
