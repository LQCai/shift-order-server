package org.celery.shift.service;

import org.celery.shift.entity.ShiftOrderDetail;
import org.celery.shift.vo.ShiftOrderDetailVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 班车预约详情表 服务类
 *
 * @author Celery
 * @since 2021-12-26
 */
public interface IShiftOrderDetailService extends BaseService<ShiftOrderDetail> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param shiftOrderDetail
	 * @return
	 */
	IPage<ShiftOrderDetailVO> selectShiftOrderDetailPage(IPage<ShiftOrderDetailVO> page, ShiftOrderDetailVO shiftOrderDetail);

	boolean deleteByIds(List<Long> ids);
}
