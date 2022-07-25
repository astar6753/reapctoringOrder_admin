package com.astar.order_admin.api;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astar.order_admin.data.request.dish.DishRequestVO;
import com.astar.order_admin.data.request.dish.OptionBlockRequestVO;
import com.astar.order_admin.data.request.dish.OptionDescRequestVO;
import com.astar.order_admin.data.response.dish.DishResponseVo;
import com.astar.order_admin.data.response.dish.DishViewResponseVO;
import com.astar.order_admin.data.response.dish.OptionBlockResponseVO;
import com.astar.order_admin.data.response.dish.OptionDescResponseVO;
import com.astar.order_admin.data.response.dish.RestaurantViewResponseVO;
import com.astar.order_admin.mapper.DishMapper;



@RestController
@RequestMapping("/api")
public class DishAPIController {
    @Autowired DishMapper dishMapper;
    
    @GetMapping("/dish/list/all") 
    public Map<String,Object> getManageDishView(@RequestParam Integer restSeq){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();

        //responseVO생성
        DishResponseVo resultData = new DishResponseVo();
        
        //1티어 레스토랑정보 파라미터ri_seq로 조회
        RestaurantViewResponseVO restaurantView = dishMapper.selectRestaurantView(restSeq);
        //resultData에 대분류 레스토랑정보 삽입
        resultData.setRestInfo(restaurantView);
        
        List<DishViewResponseVO> resultDishList = new ArrayList<DishViewResponseVO>();

        //set한값 확인용출력: 레스토랑정보만 담겨있는상태
        // System.out.println("1|"+resultData);

        //ri_seq로 2티어 음식정보list 조회 dishList를 세팅
        List<DishViewResponseVO> dishList = dishMapper.selectDishInfoByRestaurant(restSeq);
        //dishList를 1개씩 조회하면서
        //dishSeq번호로 3티어blockList정보 꺼내옴
        for(DishViewResponseVO temp : dishList){
            List<OptionBlockResponseVO> blockList = dishMapper.selectOptionBlockInfoByDish(temp.getDishSeq());
            
            List<OptionBlockResponseVO> resultBlockList = new ArrayList<OptionBlockResponseVO>();
            //blocklist를 1개씩 조회하면서
            //opt_seq번호로 4티어descList정보 꺼내옴
            for(OptionBlockResponseVO temp2 : blockList){
                
                //4티어정보 리스트 생성 및 옮겨담기
                List<OptionDescResponseVO> descList = dishMapper.selectOptionDescInfoByBlock(temp2.getDishSeq());
                temp2.setDescList(descList);

                //3티어 정보 가져와서 result리스트에 추가                
                resultBlockList.add(temp2);
            }
            //2티어정보 가져와서 result리스트에 추가
            resultDishList.add(temp);              //나머지 자투리 정보
            temp.setBlockList(resultBlockList);    //위쪽for문에서 만들어서 담아둔 3티어list
            
            resultData.setDishList(resultDishList);
        }

        // System.out.println(resultData);
        resultMap.put("key", resultData);
        return resultMap;
    }

    
    @PostMapping("/dish/list/all") 
    public Map<String,Object> postManageDishView(@RequestBody DishRequestVO data){
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
            
        dishMapper.insertDishInfoByRestaurant(data);
        
        for(OptionBlockRequestVO temp : data.getBlockList()){
            temp.setDishSeq(data.getDishSeq());
            dishMapper.insertOptionBlockInfoByDish(temp);
            
            for(OptionDescRequestVO temp2:temp.getDescList()){
                temp2.setOptionSeq(temp.getOptionSeq());
                dishMapper.insertOptionDescByBlock(temp2);
            }
        }

        resultMap.put("status", true);
        resultMap.put("message","음식정보 및 옵션정보가 추가되었습니다.");
        return resultMap;
    }
}