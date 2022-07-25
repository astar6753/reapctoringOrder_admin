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
    public void updateRestaurantImg(Integer img_seq, Integer restSeq);
    public void updateDishImg(Integer img_seq, Integer dishSeq);
    public void updateMemberImg(Integer img_seq, Integer memberSeq);
}
