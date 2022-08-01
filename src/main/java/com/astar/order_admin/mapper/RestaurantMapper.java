package com.astar.order_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.order_admin.data.request.restaurant.RestaurantAddRequestVO;
import com.astar.order_admin.data.request.restaurant.RestaurantUpdateRequestVO;
import com.astar.order_admin.data.response.category.CategoryResponseVO;
import com.astar.order_admin.data.response.restaurant.RestaurantResponseVO;

@Mapper
public interface RestaurantMapper {
    
    public void insertRestaurantInfo(RestaurantAddRequestVO data);
    public List<RestaurantResponseVO> selectRestaurantViewByUser(Integer memberSeq, Integer offset, String keyword);
    public Integer selectRestaurantViewTotalCntByUser(Integer memberSeq, String keyword);
    public Integer selectRestaurantViewPageByUser(Integer memberSeq, String keyword);
    public void updateRestaurantInfo(RestaurantUpdateRequestVO data);
    public void deleteRestaurantInfoBySeq(Integer restSeq, Integer memberSeq);

    public List<CategoryResponseVO> selectCategoryList(String keyword);
}
