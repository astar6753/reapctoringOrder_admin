package com.astar.order_admin.data.response.dish;

import java.util.List;

import lombok.Data;

@Data
public class OptionBlockResponseVO {
    private Integer optionSeq;
    private String optionTitle;
    private Integer dishSeq;
    private Integer optionAllowedNo;
    private Integer optionRequierd;
    private List<OptionDescResponseVO> descList;
}
