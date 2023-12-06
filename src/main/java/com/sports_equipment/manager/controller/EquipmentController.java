package com.sports_equipment.manager.controller;

import com.sports_equipment.manager.entity.Equipment;
import com.sports_equipment.manager.service.EquipmentService;
import com.sports_equipment.manager.util.R;
import com.sports_equipment.manager.util.http.CodeEnum;
import com.sports_equipment.manager.util.ro.PageIn;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 用户管理
 * @Date 2020/7/14 16:35
 * @Author by 尘心
 */
@Api(tags = "体育器材管理")
@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @ApiOperation("器材搜索列表")
    @PostMapping("/list")
    public R getEquipmentList(@RequestBody PageIn pageIn) {
        if (pageIn == null) {
            return R.fail(CodeEnum.PARAM_ERROR);
        }
        System.out.println(pageIn);

        return R.success(CodeEnum.SUCCESS, equipmentService.getEquipmentList(pageIn));
    }

    @ApiOperation("添加器材")
    @PostMapping("/add")
    public R addEquipment(@RequestBody Equipment equipment) {
        return R.success(CodeEnum.SUCCESS, equipmentService.addEquipment(equipment));
    }

    @ApiOperation("编辑器材")
    @PostMapping("/update")
    public R modifyEquipment(@RequestBody Equipment equipment) {
        return R.success(CodeEnum.SUCCESS, equipmentService.updateEquipment(equipment));
    }


    @ApiOperation("器材详情")
    @GetMapping("/detail")
    public R equipmentDetail(Integer id) {
        return R.success(CodeEnum.SUCCESS, equipmentService.findEquipmentById(id));
    }

    @ApiOperation("器材详情 根据ISBN获取")
    @GetMapping("/detailByIsbn")
    public R equipmentDetailByIsbn(String isbn) {
        return R.success(CodeEnum.SUCCESS, equipmentService.findEquipmentByIsbn(isbn));
    }

    @ApiOperation("删除器材")
    @GetMapping("/delete")
    public R delEquipment(Integer id) {
        equipmentService.deleteEquipment(id);
        return R.success(CodeEnum.SUCCESS);
    }

}
