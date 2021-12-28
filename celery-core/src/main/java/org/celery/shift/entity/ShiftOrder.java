package org.celery.shift.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springblade.core.mp.base.BaseEntity;

import java.sql.Time;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 班车预约表实体类
 *
 * @author Celery
 * @since 2021-12-26
 */
@Data
@TableName("c_shift_order")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShiftOrder对象", description = "班车预约表")
public class ShiftOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 区间id
     */
    @ApiModelProperty(value = "区间id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long intervalId;
    /**
     * 班次id
     */
    @ApiModelProperty(value = "班次id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shiftId;
    /**
     * 车次名
     */
    @ApiModelProperty(value = "车次名")
    private String name;
    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDate date;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "HH:mm:00")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:00")
    private Time startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Time endTime;
    /**
     * 负责人（班车司机）
     */
    @ApiModelProperty(value = "负责人（班车司机）")
    private Long principal;
    /**
     * 车型
     */
    @ApiModelProperty(value = "车型")
    private String model;
    /**
     * 总预约数
     */
    @ApiModelProperty(value = "总预约数")
    private Integer allOrderCount;
    /**
     * 有效预约数
     */
    @ApiModelProperty(value = "有效预约数")
    private Integer activeOrderCount;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


}
