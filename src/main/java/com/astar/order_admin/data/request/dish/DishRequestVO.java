package com.astar.order_admin.data.request.dish;

import java.util.List;

import lombok.Data;

@Data
public class DishRequestVO {
        private Integer dishSeq;
        private Integer restSeq;
        private String dishName;
        private Integer dishPrice;
        private String dishDescription;
        private Integer dishImgSeq;
        private String dishImgFile;
        private List<OptionBlockRequestVO> blockList;
}

/*메뉴조회
request{
    dish[1]{
            이름 
            가격 
            etc 
            opt[1]{
                    제목 
                    최대선택수
                    필수선택여부 
                    desc[1]{옵션이름 가격} 
                    desc[2]{옵션이름 가격}
                    desc[3]{옵션이름 가격}
            }
            opt[2]{제목 최대선택수 필수선택여부 
                    desc[1]{옵션이름 가격} 
                    desc[2]{옵션이름 가격}
                    desc[3]{옵션이름 가격}
            }
    }
}
*/