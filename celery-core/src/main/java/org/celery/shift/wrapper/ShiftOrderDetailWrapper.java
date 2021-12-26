package org.celery.shift.wrapper;

import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.celery.shift.entity.ShiftOrderDetail;
import org.celery.shift.vo.ShiftOrderDetailVO;

/**
 * 班车预约详情表包装类,返回视图层所需的字段
 *
 * @author Celery
 * @since 2021-12-26
 */
public class ShiftOrderDetailWrapper extends BaseEntityWrapper<ShiftOrderDetail, ShiftOrderDetailVO>  {

    public static ShiftOrderDetailWrapper build() {
        return new ShiftOrderDetailWrapper();
    }

	@Override
	public ShiftOrderDetailVO entityVO(ShiftOrderDetail shiftOrderDetail) {
		ShiftOrderDetailVO shiftOrderDetailVO = BeanUtil.copy(shiftOrderDetail, ShiftOrderDetailVO.class);

		return shiftOrderDetailVO;
	}

}
