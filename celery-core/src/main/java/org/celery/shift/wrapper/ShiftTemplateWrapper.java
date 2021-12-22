package org.celery.shift.wrapper;

import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.celery.shift.entity.ShiftTemplate;
import org.celery.shift.vo.ShiftTemplateVO;

/**
 * 班次模板表包装类,返回视图层所需的字段
 *
 * @author Celery
 * @since 2021-12-22
 */
public class ShiftTemplateWrapper extends BaseEntityWrapper<ShiftTemplate, ShiftTemplateVO>  {

    public static ShiftTemplateWrapper build() {
        return new ShiftTemplateWrapper();
    }

	@Override
	public ShiftTemplateVO entityVO(ShiftTemplate shiftTemplate) {
		ShiftTemplateVO shiftTemplateVO = BeanUtil.copy(shiftTemplate, ShiftTemplateVO.class);

		return shiftTemplateVO;
	}

}
