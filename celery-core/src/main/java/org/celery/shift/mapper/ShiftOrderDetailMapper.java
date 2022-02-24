package org.celery.shift.mapper;

import org.celery.shift.entity.ShiftOrderDetail;
import org.celery.shift.vo.ShiftOrderDetailVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 班车预约详情表 Mapper 接口
 *
 * @author Celery
 * @since 2021-12-26
 */
public interface ShiftOrderDetailMapper extends BaseMapper<ShiftOrderDetail> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param shiftOrderDetail
	 * @return
	 */
	List<ShiftOrderDetailVO> selectShiftOrderDetailPage(IPage page, ShiftOrderDetailVO shiftOrderDetail);

	void deleteByIds(List<Long> ids);
}
