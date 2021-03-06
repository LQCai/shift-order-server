package org.celery.shift.service;

import org.celery.shift.entity.ShiftTemplate;
import org.celery.shift.vo.ShiftTemplateVO;
import org.springblade.core.mp.base.BaseService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * 班次模板表 服务类
 *
 * @author Celery
 * @since 2021-12-22
 */
public interface IShiftTemplateService extends BaseService<ShiftTemplate> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param shiftTemplate
	 * @return
	 */
	IPage<ShiftTemplateVO> selectShiftTemplatePage(IPage<ShiftTemplateVO> page, ShiftTemplateVO shiftTemplate);

	/**
	 * 导入用户数据
	 *
	 * @param list
	 * @return
	 */
	void importShift(List<Map<Integer, Map<Integer, String>>> list);

}
