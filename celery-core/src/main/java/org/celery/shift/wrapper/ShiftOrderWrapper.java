package org.celery.shift.wrapper;

import org.celery.shift.entity.Interval;
import org.celery.shift.service.IIntervalService;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.celery.shift.entity.ShiftOrder;
import org.celery.shift.vo.ShiftOrderVO;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.SpringUtil;

/**
 * 班车预约表包装类,返回视图层所需的字段
 *
 * @author Celery
 * @since 2021-12-26
 */
public class ShiftOrderWrapper extends BaseEntityWrapper<ShiftOrder, ShiftOrderVO>  {

    public static ShiftOrderWrapper build() {
        return new ShiftOrderWrapper();
    }

	@Override
	public ShiftOrderVO entityVO(ShiftOrder shiftOrder) {
		ShiftOrderVO shiftOrderVO = BeanUtil.copy(shiftOrder, ShiftOrderVO.class);
		IIntervalService intervalService = SpringUtil.getBean(IIntervalService.class);

		Interval interval = intervalService.getById(shiftOrder.getIntervalId());
		if (Func.isNotEmpty(interval)) {
			shiftOrderVO.setIntervalName(interval.getName());
		}
		return shiftOrderVO;
	}

}
