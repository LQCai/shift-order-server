package org.celery.shift.mapper;

import org.celery.shift.entity.ShiftTemplate;
import org.celery.shift.vo.ShiftTemplateVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * 班次模板表 Mapper 接口
 *
 * @author Celery
 * @since 2021-12-22
 */
public interface ShiftTemplateMapper extends BaseMapper<ShiftTemplate> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param shiftTemplate
	 * @return
	 */
	List<ShiftTemplateVO> selectShiftTemplatePage(IPage page, ShiftTemplateVO shiftTemplate);

}
