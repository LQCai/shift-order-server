package org.celery.shift.vo;

import org.celery.shift.entity.Interval;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 区间表视图实体类
 *
 * @author Celery
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "IntervalVO对象", description = "区间表")
public class IntervalVO extends Interval {
	private static final long serialVersionUID = 1L;

}
