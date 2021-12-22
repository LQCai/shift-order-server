package org.celery.shift.service;

import org.celery.shift.entity.Interval;
import org.celery.shift.vo.IntervalVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 区间表 服务类
 *
 * @author Celery
 * @since 2021-12-22
 */
public interface IIntervalService extends BaseService<Interval> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param interval
	 * @return
	 */
	IPage<IntervalVO> selectIntervalPage(IPage<IntervalVO> page, IntervalVO interval);

}
