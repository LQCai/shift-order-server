package org.celery.shift.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springblade.core.mp.base.BaseEntity;

import java.sql.Time;
import java.time.LocalTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 班次模板表实体类
 *
 * @author Celery
 * @since 2021-12-22
 */
@Data
@TableName("c_shift_template")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShiftTemplate对象", description = "班次模板表")
public class ShiftTemplate extends BaseEntity {

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
     * 车次名
     */
    @ApiModelProperty(value = "车次名")
    private String name;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern="HH:mm:00")
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:00")
    @ApiModelProperty(value = "开始时间")
    private Time startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private LocalTime endTime;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


}
