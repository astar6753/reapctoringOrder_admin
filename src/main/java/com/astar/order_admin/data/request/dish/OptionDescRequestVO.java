package com.astar.order_admin.data.request.dish;

import lombok.Data;

@Data
public class OptionDescRequestVO {
    private Integer descSeq;
    private Integer optionSeq;
    private String descText;
    private Integer dishPrice;
}
