package org.celery.shift.dto;

import org.celery.shift.entity.Interval;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 区间表数据传输对象实体类
 *
 * @author Celery
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IntervalDTO extends Interval {
	private static final long serialVersionUID = 1L;

}
