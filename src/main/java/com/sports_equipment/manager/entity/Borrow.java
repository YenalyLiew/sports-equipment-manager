package com.sports_equipment.manager.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description 借阅表
 * @Date 2020/7/14 16:01
 * @Author by 尘心
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "borrow")
public class Borrow {

    @ApiModelProperty("主键ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("器材ID")
    private String equipmentId;

    @ApiModelProperty("借用时间")
    private Date createTime;

    @ApiModelProperty("归还时间")
    private Date endTime;

    @ApiModelProperty("实际归还时间")
    private Date updateTime;

    @ApiModelProperty("是否归还? 0 已归还/1 未归还")
    private Integer ret;
    // json: {"userId":userId,"bookId":bookId,"createTime":createTime,"endTime":endTime,"updateTime":updateTime}
}
