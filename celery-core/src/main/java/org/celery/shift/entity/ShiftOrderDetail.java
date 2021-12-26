package org.celery.shift.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springblade.core.mp.base.BaseEntity;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 班车预约详情表实体类
 *
 * @author Celery
 * @since 2021-12-26
 */
@Data
@TableName("c_shift_order_detail")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShiftOrderDetail对象", description = "班车预约详情表")
public class ShiftOrderDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
  private Long id;
    /**
     * 日班次id
     */
    @ApiModelProperty(value = "日班次id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shiftOrderId;
    /**
     * 班次id
     */
    @ApiModelProperty(value = "班次id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long shiftId;
    /**
     * 区间id
     */
    @ApiModelProperty(value = "区间id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long intervalId;
    /**
     * 日期
     */
    @ApiModelProperty(value = "日期")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDate date;
    /**
     * 预约人id
     */
    @ApiModelProperty(value = "预约人id")
    private Long orderUserId;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


}
