package com.sports_equipment.manager.dao;

import com.sports_equipment.manager.entity.Equipment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description 图书
 * @Date 2020/7/14 19:57
 * @Author by 尘心
 */
@Mapper
@Component
public interface EquipmentMapper {

    /**
     * 模糊分页查询用户
     * @param keyword 关键字
     * @return
     */
    List<Equipment> findEquipmentsByLike(String keyword);

    /**
     * 编辑用户
     * @param map
     * @return
     */
    int updateEquipment(Map<String, Object> map);
}
