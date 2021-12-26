package org.celery.shift.dto;

import org.celery.shift.entity.ShiftOrderDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 班车预约详情表数据传输对象实体类
 *
 * @author Celery
 * @since 2021-12-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShiftOrderDetailDTO extends ShiftOrderDetail {
	private static final long serialVersionUID = 1L;

}
