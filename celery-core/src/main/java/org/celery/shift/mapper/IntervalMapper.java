package org.celery.shift.mapper;

import org.celery.shift.entity.Interval;
import org.celery.shift.vo.IntervalVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 区间表 Mapper 接口
 *
 * @author Celery
 * @since 2021-12-22
 */
public interface IntervalMapper extends BaseMapper<Interval> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param interval
	 * @return
	 */
	List<IntervalVO> selectIntervalPage(IPage page, IntervalVO interval);

}
