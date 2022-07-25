package com.astar.order_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.order_admin.data.request.dish.DishRequestVO;
import com.astar.order_admin.data.request.dish.OptionBlockRequestVO;
import com.astar.order_admin.data.request.dish.OptionDescRequestVO;
import com.astar.order_admin.data.response.dish.DishViewResponseVO;
import com.astar.order_admin.data.response.dish.OptionBlockResponseVO;
import com.astar.order_admin.data.response.dish.OptionDescResponseVO;
import com.astar.order_admin.data.response.dish.RestaurantViewResponseVO;

@Mapper
public interface DishMapper {
    public RestaurantViewResponseVO selectRestaurantView(Integer restSeq);
    public List<DishViewResponseVO> selectDishInfoByRestaurant(Integer restSeq);
    public List<OptionBlockResponseVO> selectOptionBlockInfoByDish(Integer dishSeq);
    public List<OptionDescResponseVO> selectOptionDescInfoByBlock(Integer optionSeq);

    public void insertDishInfoByRestaurant(DishRequestVO data);
    public void insertOptionBlockInfoByDish(OptionBlockRequestVO data);
    public void insertOptionDescByBlock(OptionDescRequestVO data);

}
