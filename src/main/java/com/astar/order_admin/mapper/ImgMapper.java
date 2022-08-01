package com.astar.order_admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.astar.order_admin.data.request.img.ImgRequstVO;
import com.astar.order_admin.data.response.img.ImgResponseVO;

@Mapper
public interface ImgMapper {
    public List<ImgResponseVO> selectImgInfoBySeq(Integer memberSeq, Integer typeNo, Integer offset);
    public void insertImgInfo(ImgRequstVO data);
    public String selectImgBackNameByImgSeq(Integer imgSeq);

    public void deleteImgInfoByImgSeq(Integer imgSeq);
    public void updateRestaurantImg(Integer imgSeq, Integer restSeq);
    public void updateDishImg(Integer imgSeq, Integer dishSeq);
    public void updateMemberImg(Integer imgSeq, Integer memberSeq);
}
