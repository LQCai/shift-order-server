package org.celery.shift.vo;

import org.celery.shift.entity.ShiftOrderDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 班车预约详情表视图实体类
 *
 * @author Celery
 * @since 2021-12-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShiftOrderDetailVO对象", description = "班车预约详情表")
public class ShiftOrderDetailVO extends ShiftOrderDetail {
	private static final long serialVersionUID = 1L;

	private String intervalName;

	private String userName;

	private String code;

	private String phone;

	private String statusName;
}
