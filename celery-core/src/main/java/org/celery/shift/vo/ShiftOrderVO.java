package org.celery.shift.vo;

import org.celery.shift.entity.ShiftOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 班车预约表视图实体类
 *
 * @author Celery
 * @since 2021-12-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShiftOrderVO对象", description = "班车预约表")
public class ShiftOrderVO extends ShiftOrder {
	private static final long serialVersionUID = 1L;

}
