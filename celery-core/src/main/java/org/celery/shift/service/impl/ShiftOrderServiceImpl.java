package org.celery.shift.service.impl;

import org.celery.shift.entity.ShiftOrder;
import org.celery.shift.vo.ShiftOrderVO;
import org.celery.shift.mapper.ShiftOrderMapper;
import org.celery.shift.service.IShiftOrderService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 班车预约表 服务实现类
 *
 * @author Celery
 * @since 2021-12-26
 */
@Service
public class ShiftOrderServiceImpl extends BaseServiceImpl<ShiftOrderMapper, ShiftOrder> implements IShiftOrderService {

	@Override
	public IPage<ShiftOrderVO> selectShiftOrderPage(IPage<ShiftOrderVO> page, ShiftOrderVO shiftOrder) {
		return page.setRecords(baseMapper.selectShiftOrderPage(page, shiftOrder));
	}

}
