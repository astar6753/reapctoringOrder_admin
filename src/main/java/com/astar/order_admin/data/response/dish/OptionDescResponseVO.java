package com.astar.order_admin.data.response.dish;

import lombok.Data;

@Data
public class OptionDescResponseVO {
    private Integer descSeq;
    private Integer optionSeq;    
    private String descText;
    private Integer dishPrice;
}
