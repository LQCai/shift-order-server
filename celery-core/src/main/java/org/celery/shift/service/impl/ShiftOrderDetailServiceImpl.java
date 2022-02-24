package org.celery.shift.service.impl;

import org.celery.shift.entity.ShiftOrderDetail;
import org.celery.shift.vo.ShiftOrderDetailVO;
import org.celery.shift.mapper.ShiftOrderDetailMapper;
import org.celery.shift.service.IShiftOrderDetailService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 班车预约详情表 服务实现类
 *
 * @author Celery
 * @since 2021-12-26
 */
@Service
public class ShiftOrderDetailServiceImpl extends BaseServiceImpl<ShiftOrderDetailMapper, ShiftOrderDetail> implements IShiftOrderDetailService {

	@Override
	public IPage<ShiftOrderDetailVO> selectShiftOrderDetailPage(IPage<ShiftOrderDetailVO> page, ShiftOrderDetailVO shiftOrderDetail) {
		return page.setRecords(baseMapper.selectShiftOrderDetailPage(page, shiftOrderDetail));
	}

	@Override
	public boolean deleteByIds(List<Long> ids) {
		if (Func.isEmpty(ids)) {
			return false;
		}
		baseMapper.deleteByIds(ids);
		return true;
	}

}
