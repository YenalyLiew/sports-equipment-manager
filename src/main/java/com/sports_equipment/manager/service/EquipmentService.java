package com.sports_equipment.manager.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sports_equipment.manager.dao.EquipmentMapper;
import com.sports_equipment.manager.entity.Equipment;
import com.sports_equipment.manager.repos.EquipmentRepository;
import com.sports_equipment.manager.util.ro.PageIn;
import com.sports_equipment.manager.util.vo.EquipmentOut;
import com.sports_equipment.manager.util.vo.PageOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description 图书业务类
 * @Date 2020/7/14 16:31
 * @Author by 尘心
 */
@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentMapper equipmentMapper;


    /**
     * 添加用户
     *
     * @param equipment 图书
     * @return 返回添加的图书
     */
    public Equipment addEquipment(Equipment equipment) {
        return equipmentRepository.saveAndFlush(equipment);
    }

    /**
     * 编辑用户
     *
     * @param equipment 图书对象
     * @return true or false
     */
    public boolean updateEquipment(Equipment equipment) {
        return equipmentMapper.updateEquipment(BeanUtil.beanToMap(equipment)) > 0;
    }

    /**
     * 图书详情
     *
     * @param id 主键
     * @return 图书详情
     */
    public EquipmentOut findEquipmentById(Integer id) {
        Optional<Equipment> optional = equipmentRepository.findById(id);
        if (optional.isPresent()) {
            Equipment equipment = optional.get();
            EquipmentOut out = new EquipmentOut();
            BeanUtil.copyProperties(equipment, out);
            out.setPublishTime(DateUtil.format(equipment.getPublishTime(), "yyyy-MM-dd"));
            return out;
        }
        return null;
    }

    public Equipment findEquipment(Integer id) {
        Optional<Equipment> optional = equipmentRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * ISBN查询
     *
     * @param isbn
     * @return
     */
    public EquipmentOut findEquipmentByIsbn(String isbn) {
        Equipment equipment = equipmentRepository.findFirstByEquipmentId(isbn);
        EquipmentOut out = new EquipmentOut();
        if (equipment == null) {
            return out;
        }
        BeanUtil.copyProperties(equipment, out);
        out.setPublishTime(DateUtil.format(equipment.getPublishTime(), "yyyy-MM-dd"));
        return out;
    }

    /**
     * 删除图书
     *
     * @param id 主键
     * @return true or false
     */
    public void deleteEquipment(Integer id) {
        equipmentRepository.deleteById(id);
    }


    /**
     * 图书搜索查询(mybatis 分页)
     *
     * @param pageIn
     * @return
     */
    public PageOut getEquipmentList(PageIn pageIn) {

        PageHelper.startPage(pageIn.getCurrPage(), pageIn.getPageSize());
        System.out.println(pageIn);
        List<Equipment> list = equipmentMapper.findEquipmentsByLike(pageIn.getKeyword());
        PageInfo<Equipment> pageInfo = new PageInfo<>(list);

        List<EquipmentOut> eqOuts = new ArrayList<>();
        for (Equipment equipment : pageInfo.getList()) {
            EquipmentOut out = new EquipmentOut();
            BeanUtil.copyProperties(equipment, out);
            out.setPublishTime(DateUtil.format(equipment.getPublishTime(), "yyyy-MM-dd"));
            eqOuts.add(out);
        }

        // 自定义分页返回对象
        PageOut pageOut = new PageOut();
        pageOut.setList(eqOuts);
        pageOut.setTotal((int) pageInfo.getTotal());
        pageOut.setCurrPage(pageInfo.getPageNum());
        pageOut.setPageSize(pageInfo.getPageSize());
        return pageOut;
    }


}
