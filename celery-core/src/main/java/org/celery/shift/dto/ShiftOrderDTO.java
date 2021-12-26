package org.celery.shift.dto;

import org.celery.shift.entity.ShiftOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 班车预约表数据传输对象实体类
 *
 * @author Celery
 * @since 2021-12-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShiftOrderDTO extends ShiftOrder {
	private static final long serialVersionUID = 1L;

}
