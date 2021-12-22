package org.celery.shift.dto;

import org.celery.shift.entity.ShiftTemplate;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 班次模板表数据传输对象实体类
 *
 * @author Celery
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ShiftTemplateDTO extends ShiftTemplate {
	private static final long serialVersionUID = 1L;

}
