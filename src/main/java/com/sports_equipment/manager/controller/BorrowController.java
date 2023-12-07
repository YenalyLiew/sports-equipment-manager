package com.sports_equipment.manager.controller;

import cn.hutool.core.date.DateUtil;
import com.sports_equipment.manager.entity.Borrow;
import com.sports_equipment.manager.service.BorrowService;
import com.sports_equipment.manager.service.EquipmentService;
import com.sports_equipment.manager.util.R;
import com.sports_equipment.manager.util.consts.Constants;
import com.sports_equipment.manager.util.http.CodeEnum;
import com.sports_equipment.manager.util.vo.EquipmentBack;
import com.sports_equipment.manager.util.vo.EquipmentOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 用户管理
 * @Date 2020/7/14 16:35
 * @Author by 尘心
 */
@Api(tags = "借用管理")
@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private EquipmentService equipmentService;

    @ApiOperation("借用列表")
    @GetMapping("/list")
    public R getBorrowList(Integer userId) {
        return R.success(CodeEnum.SUCCESS, borrowService.findAllBorrowByUserId(userId));
    }

    @ApiOperation("借用器材")
    @PostMapping("/add")
    public R addBorrow(@RequestBody Borrow borrow) {
        Integer result = borrowService.addBorrow(borrow);
        if (result == Constants.BOOK_BORROWED) {
            return R.success(CodeEnum.BOOK_BORROWED);
        } else if (result == Constants.USER_SIZE_NOT_ENOUGH) {
            return R.success(CodeEnum.USER_NOT_ENOUGH);
        } else if (result == Constants.BOOK_SIZE_NOT_ENOUGH) {
            return R.success(CodeEnum.BOOK_NOT_ENOUGH);
        }
        return R.success(CodeEnum.SUCCESS, Constants.OK);
    }

    @ApiOperation("编辑借用")
    @PostMapping("/update")
    public R modifyBorrow(@RequestBody Borrow borrow) {
        return R.success(CodeEnum.SUCCESS, borrowService.updateBorrow(borrow));
    }


    @ApiOperation("借用详情")
    @GetMapping("/detail")
    public R borrowDetail(Integer id) {
        return R.success(CodeEnum.SUCCESS, borrowService.findById(id));
    }

    @ApiOperation("删除归还记录")
    @GetMapping("/delete")
    public R delBorrow(Integer id) {
        borrowService.deleteBorrow(id);
        return R.success(CodeEnum.SUCCESS);
    }


    @ApiOperation("已借用列表")
    @GetMapping("/borrowed")
    public R borrowedList(Integer userId) {
        List<EquipmentBack> outs = new ArrayList<>();
        if (userId != null && userId > 0) {
            // 获取所有 已借用 未归还书籍
            List<Borrow> borrows = borrowService.findBorrowsByUserIdAndRet(userId, Constants.NO);
            for (Borrow borrow : borrows) {
                EquipmentBack backOut = new EquipmentBack();
                EquipmentOut out = equipmentService.findEquipmentByIsbn(borrow.getEquipmentId());
                System.out.println("!!!!!!!" + out);
                BeanUtils.copyProperties(out, backOut);

                backOut.setBorrowTime(DateUtil.format(borrow.getCreateTime(), Constants.DATE_FORMAT));

                String endTimeStr = DateUtil.format(borrow.getEndTime(), Constants.DATE_FORMAT);
                backOut.setEndTime(endTimeStr);
                // 判断是否逾期
                String toDay = DateUtil.format(new Date(), Constants.DATE_FORMAT);
                int i = toDay.compareTo(endTimeStr);
                if (i > 0) {
                    backOut.setLate(Constants.YES_STR);
                } else {
                    backOut.setLate(Constants.NO_STR);
                }

                outs.add(backOut);
            }
        }

        return R.success(CodeEnum.SUCCESS, outs);
    }

    @ApiOperation("归还器材")
    @PostMapping("/ret")
    public R retEquipment(Integer userId, int equipmentId) {
        // 归还图书
        borrowService.retEquipment(userId, equipmentId);
        return R.success(CodeEnum.SUCCESS);
    }

}
