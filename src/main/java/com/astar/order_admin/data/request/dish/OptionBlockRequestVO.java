package com.astar.order_admin.data.request.dish;

import java.util.List;

import lombok.Data;

@Data
public class OptionBlockRequestVO {
    private Integer optionSeq;
    private String optionTitle;
    private Integer dishSeq;
    private Integer optionAllowedNo;
    private Integer optionRequierd;
    private List<OptionDescRequestVO> descList;
    
}
