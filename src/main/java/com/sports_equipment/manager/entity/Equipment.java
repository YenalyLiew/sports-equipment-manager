package com.sports_equipment.manager.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name = "equipment")
public class Equipment {
    @ApiModelProperty("主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("器材编码")
    private String equipmentId;

    @ApiModelProperty("器材名称")
    private String name;

    @ApiModelProperty("供应商")
    private String supplier;

    @ApiModelProperty("单价")
    private Double price;

    @ApiModelProperty("库存")
    private Integer size;

    @ApiModelProperty("分类")
    private String type;

    @ApiModelProperty("出厂时间")
    private Date publishTime;
}
