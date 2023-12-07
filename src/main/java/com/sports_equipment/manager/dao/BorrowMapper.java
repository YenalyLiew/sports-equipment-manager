package com.sports_equipment.manager.dao;

import com.sports_equipment.manager.entity.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description 借阅管理
 * @Date 2020/7/15 16:45
 * @Author by 尘心
 */
@Mapper
@Component
public interface BorrowMapper {

    @Update("update borrow set user_id = #{userId},book_id = #{equipmentId},update_time = #{updateTime} where id = #{id}")
    int updateBorrow(Borrow borrow);

    @Select("select * from borrow where user_id = #{userId} and equipment_id = #{equipmentId}")
    Borrow findBorrowByUserIdAndEquipmentId(@Param("userId") Integer userId, @Param("equipmentId") String equipmentId);

    int updateBor(Map<String, Object> map);
}