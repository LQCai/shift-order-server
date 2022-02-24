package org.celery.shift.mapper;

import org.celery.shift.entity.ShiftOrder;
import org.celery.shift.vo.ShiftOrderVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 班车预约表 Mapper 接口
 *
 * @author Celery
 * @since 2021-12-26
 */
public interface ShiftOrderMapper extends BaseMapper<ShiftOrder> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param shiftOrder
	 * @return
	 */
	List<ShiftOrderVO> selectShiftOrderPage(IPage page, ShiftOrderVO shiftOrder);

	void deleteByIds(List<Long> ids);
}
