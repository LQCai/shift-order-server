package org.celery.shift.service.impl;

import org.celery.shift.entity.Interval;
import org.celery.shift.vo.IntervalVO;
import org.celery.shift.mapper.IntervalMapper;
import org.celery.shift.service.IIntervalService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 区间表 服务实现类
 *
 * @author Celery
 * @since 2021-12-22
 */
@Service
public class IntervalServiceImpl extends BaseServiceImpl<IntervalMapper, Interval> implements IIntervalService {

	@Override
	public IPage<IntervalVO> selectIntervalPage(IPage<IntervalVO> page, IntervalVO interval) {
		return page.setRecords(baseMapper.selectIntervalPage(page, interval));
	}

}
