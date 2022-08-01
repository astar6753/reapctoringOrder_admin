package com.astar.order_admin.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/manage")
public class ManageController {
    
    @GetMapping("/customer")  //고객 주문 이력 조회
    public String getManageCustomer() {
        return "/manage/customer";
    }
    @GetMapping("/restaurant")  //영업장 관리
    public String getManageRestaurant(@RequestParam @Nullable Integer page) {
        if(page==null||page<0) page=0;
        return "/manage/restaurant";
    }
    @GetMapping("/order")  //주문 관리
    public String getManageOrder() {
        return "/manage/order";
    }    
    @GetMapping("/dish")  //메뉴 관리
    public String getManageDish(@RequestParam @Nullable Integer seq) {
        return "/manage/dish";
    }
    
}
