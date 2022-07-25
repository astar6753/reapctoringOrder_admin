package com.astar.order_admin.data.response.dish;

import java.util.List;


import lombok.Data;

@Data
public class DishViewResponseVO {
        private Integer dishSeq;
        private Integer restSeq;
        private String dishName;
        private Integer dishPrice;
        private String dishDescription;
        private Integer dishImgSeq;
        private String dishImgFile;
        private List<OptionBlockResponseVO> blockList;
}