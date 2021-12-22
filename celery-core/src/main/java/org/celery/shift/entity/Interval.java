package org.celery.shift.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springblade.core.mp.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 区间表实体类
 *
 * @author Celery
 * @since 2021-12-22
 */
@Data
@TableName("c_interval")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Interval对象", description = "区间表")
public class Interval extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
  private Long id;
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    private String code;
    /**
     * 区间名
     */
    @ApiModelProperty(value = "区间名")
    private String name;
    /**
     * 联系人姓名
     */
    @ApiModelProperty(value = "联系人姓名")
    private String contractUser;
    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private Integer contractTelephone;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


}
