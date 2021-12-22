package org.celery.shift.service.impl;

import org.celery.shift.entity.ShiftTemplate;
import org.celery.shift.vo.ShiftTemplateVO;
import org.celery.shift.mapper.ShiftTemplateMapper;
import org.celery.shift.service.IShiftTemplateService;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 班次模板表 服务实现类
 *
 * @author Celery
 * @since 2021-12-22
 */
@Service
public class ShiftTemplateServiceImpl extends BaseServiceImpl<ShiftTemplateMapper, ShiftTemplate> implements IShiftTemplateService {

	@Override
	public IPage<ShiftTemplateVO> selectShiftTemplatePage(IPage<ShiftTemplateVO> page, ShiftTemplateVO shiftTemplate) {
		return page.setRecords(baseMapper.selectShiftTemplatePage(page, shiftTemplate));
	}

}
