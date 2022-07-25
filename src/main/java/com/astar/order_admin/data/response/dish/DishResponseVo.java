package com.astar.order_admin.data.response.dish;

import java.util.List;

import lombok.Data;

@Data
public class DishResponseVo {
    private RestaurantViewResponseVO restInfo;
    private List<DishViewResponseVO> dishList;
}
