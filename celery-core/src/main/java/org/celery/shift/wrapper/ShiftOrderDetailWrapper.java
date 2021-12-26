package org.celery.shift.wrapper;

import org.celery.shift.entity.Interval;
import org.celery.shift.service.IIntervalService;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.celery.shift.entity.ShiftOrderDetail;
import org.celery.shift.vo.ShiftOrderDetailVO;
import org.springblade.core.tool.utils.Func;
import org.springblade.core.tool.utils.SpringUtil;
import org.springblade.modules.system.entity.User;
import org.springblade.modules.system.service.IUserService;

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
		IIntervalService intervalService = SpringUtil.getBean(IIntervalService.class);
		IUserService userService = SpringUtil.getBean(IUserService.class);

		Interval interval = intervalService.getById(shiftOrderDetail.getIntervalId());
		if (Func.isNotEmpty(interval)) {
			shiftOrderDetailVO.setIntervalName(interval.getName());
		}

		User user = userService.getById(shiftOrderDetail.getOrderUserId());
		if (Func.isNotEmpty(user)) {
			shiftOrderDetailVO.setUserName(user.getName());
			shiftOrderDetailVO.setCode(user.getCode());
			shiftOrderDetailVO.setPhone(user.getPhone());
		}

		return shiftOrderDetailVO;
	}

}
