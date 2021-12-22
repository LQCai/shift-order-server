package org.celery.shift.vo;

import org.celery.shift.entity.ShiftTemplate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;

/**
 * 班次模板表视图实体类
 *
 * @author Celery
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ShiftTemplateVO对象", description = "班次模板表")
public class ShiftTemplateVO extends ShiftTemplate {
	private static final long serialVersionUID = 1L;

}
