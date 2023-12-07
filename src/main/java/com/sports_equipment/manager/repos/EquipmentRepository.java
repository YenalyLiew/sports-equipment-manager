package com.sports_equipment.manager.repos;

import com.sports_equipment.manager.entity.Equipment;
import com.sports_equipment.manager.util.vo.EquipmentOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 书籍
 * @Date 2020/7/14 16:12
 * @Author by 尘心
 */
@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    Equipment findFirstByEquipmentId(String equipmentId);
}
