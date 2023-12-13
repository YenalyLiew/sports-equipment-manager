package com.sports_equipment.manager.service;

import com.sports_equipment.manager.dao.BorrowMapper;
import com.sports_equipment.manager.entity.Borrow;
import com.sports_equipment.manager.entity.Equipment;
import com.sports_equipment.manager.entity.Users;
import com.sports_equipment.manager.repos.BorrowRepository;
import com.sports_equipment.manager.util.consts.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Description 借阅管理
 * @Date 2020/7/15 16:46
 * @Author by 尘心
 */
@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private UserService userService;

    /**
     * 添加
     * （添加事物）
     */
    @Transactional
    public Integer addBorrow(Borrow borrow) {
        Equipment equipment = equipmentService.findEquipment(borrow.getEquipmentId());
        Users users = userService.findUserById(borrow.getUserId());

        // 查询是否已经借阅过该器材
        Borrow bor = findBorrowByUserIdAndEquipmentId(users.getId(), equipment.getEquipmentId());
        if (bor != null) {
            Integer ret = bor.getRet();
            if (ret != null) {
                // 已借阅, 未归还 不可再借
                if (ret == Constants.NO) {
                    return Constants.BOOK_BORROWED;
                }
            }
        }

        // 库存数量减一
        int size = equipment.getSize();
        if (size > 0) {
            size--;
            equipment.setSize(size);
            equipmentService.updateEquipment(equipment);
        } else {
            return Constants.BOOK_SIZE_NOT_ENOUGH;
        }

        // 用户可借数量减一
        int userSize = users.getSize();
        if (userSize > 0) {
            userSize--;
            users.setSize(userSize);
            userService.updateUser(users);
        } else {
            return Constants.USER_SIZE_NOT_ENOUGH;
        }


        // 添加借阅信息, 借阅默认为未归还状态
        borrow.setRet(Constants.NO);
        borrowRepository.saveAndFlush(borrow);

        // 一切正常
        return Constants.OK;
    }

    /**
     * user id查询所有借阅信息
     */
    public List<Borrow> findAllBorrowByUserId(Integer userId) {
        return borrowRepository.findBorrowByUserId(userId);
    }

    /**
     * user id查询所有 已借阅信息
     */
    public List<Borrow> findBorrowsByUserIdAndRet(Integer userId, Integer ret) {
        return borrowRepository.findBorrowsByUserIdAndRet(userId, ret);
    }


    /**
     * 详情
     */
    public Borrow findById(Integer id) {
        Optional<Borrow> optional = borrowRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * 编辑
     */
    public boolean updateBorrow(Borrow borrow) {
        return borrowMapper.updateBorrow(borrow) > 0;
    }


    /**
     * 编辑
     */
    public Borrow updateBorrowByRepo(Borrow borrow) {
        return borrowRepository.saveAndFlush(borrow);
    }

    /**
     * s删除
     */
    public void deleteBorrow(Integer id) {
        borrowRepository.deleteById(id);
    }

    /**
     * 查询用户某一条借阅信息
     *
     * @param userId 用户id
     * @param equipmentId 器材id
     */
    public Borrow findBorrowByUserIdAndEquipmentId(int userId, String equipmentId) {
        return borrowMapper.findBorrowByUserIdAndEquipmentId(userId, equipmentId);
    }

    /**
     * 归还器材, 使用事务保证 ACID
     *
     * @param userId 用户Id
     * @param equipmentId 器材id
     */
    @Transactional(rollbackFor = Exception.class)
    public void retEquipment(int userId, String equipmentId) {
        // 用户可借数量加1
        Users user = userService.findUserById(userId);
        Integer size = user.getSize();
        size++;
        user.setSize(size);
        userService.updateUser(user);


        // 器材库存加1
        Equipment equipment = equipmentService.findEquipment(equipmentId);
        Integer equipmentSize = equipment.getSize();
        equipmentSize++;
        equipment.setSize(equipmentSize);
        equipmentService.updateEquipment(equipment);
        // 借阅记录改为已归还,删除记录
        Borrow borrow = this.findBorrowByUserIdAndEquipmentId(userId, equipmentId);
//        borrow.setRet(Constants.YES);
//        borrow.setUpdateTime(new Date());
//        borrowMapper.updateBor(BeanUtil.beanToMap(borrow))>0;
        this.deleteBorrow(borrow.getId());
    }
}
