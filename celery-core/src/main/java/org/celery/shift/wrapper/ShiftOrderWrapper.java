package org.celery.shift.wrapper;

import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.celery.shift.entity.ShiftOrder;
import org.celery.shift.vo.ShiftOrderVO;

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

		return shiftOrderVO;
	}

}
