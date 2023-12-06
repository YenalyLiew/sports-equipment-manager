package com.sports_equipment.manager.util.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class EquipmentOut {

    @ApiModelProperty("主键")
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

    @ApiModelProperty("出版时间")
    private String publishTime;
}
