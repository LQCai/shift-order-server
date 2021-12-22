package org.celery.shift.wrapper;

import lombok.AllArgsConstructor;
import org.springblade.core.mp.support.BaseEntityWrapper;
import org.springblade.core.tool.utils.BeanUtil;
import org.celery.shift.entity.Interval;
import org.celery.shift.vo.IntervalVO;

/**
 * 区间表包装类,返回视图层所需的字段
 *
 * @author Celery
 * @since 2021-12-22
 */
public class IntervalWrapper extends BaseEntityWrapper<Interval, IntervalVO>  {

    public static IntervalWrapper build() {
        return new IntervalWrapper();
    }

	@Override
	public IntervalVO entityVO(Interval interval) {
		IntervalVO intervalVO = BeanUtil.copy(interval, IntervalVO.class);

		return intervalVO;
	}

}
