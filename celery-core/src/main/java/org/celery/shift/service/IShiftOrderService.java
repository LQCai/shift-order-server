package org.celery.shift.service;

import org.celery.shift.entity.ShiftOrder;
import org.celery.shift.vo.ShiftOrderVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 班车预约表 服务类
 *
 * @author Celery
 * @since 2021-12-26
 */
public interface IShiftOrderService extends BaseService<ShiftOrder> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param shiftOrder
	 * @return
	 */
	IPage<ShiftOrderVO> selectShiftOrderPage(IPage<ShiftOrderVO> page, ShiftOrderVO shiftOrder);

	boolean deleteByIds(List<Long> ids);
}
