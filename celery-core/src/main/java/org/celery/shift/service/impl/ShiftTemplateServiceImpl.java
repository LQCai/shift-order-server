package org.celery.shift.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.celery.shift.entity.Interval;
import org.celery.shift.entity.ShiftTemplate;
import org.celery.shift.service.IIntervalService;
import org.celery.shift.vo.ShiftTemplateVO;
import org.celery.shift.mapper.ShiftTemplateMapper;
import org.celery.shift.service.IShiftTemplateService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.constant.BladeConstant;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 班次模板表 服务实现类
 *
 * @author Celery
 * @since 2021-12-22
 */
@Service
@AllArgsConstructor
@Slf4j
public class ShiftTemplateServiceImpl extends BaseServiceImpl<ShiftTemplateMapper, ShiftTemplate> implements IShiftTemplateService {

	private final IIntervalService intervalService;

	@Override
	public IPage<ShiftTemplateVO> selectShiftTemplatePage(IPage<ShiftTemplateVO> page, ShiftTemplateVO shiftTemplate) {
		return page.setRecords(baseMapper.selectShiftTemplatePage(page, shiftTemplate));
	}

	@Override
	public void importShift(List<Map<Integer, Map<Integer, String>>> list) {
		log.info("--start---导入判断----");
		if (Func.isEmpty(list.get(1))) {
			throw new ServiceException("导入数据为空");
		}
		Map<Integer, String> intervalStringMap = list.get(1).get(1);
		Map<Integer, Long> intervalIdMap = new HashMap<>();
		for (int key : intervalStringMap.keySet()) {
			if (key > 0) {
				Interval interval = intervalService.getOne(Wrappers.<Interval>lambdaQuery()
						.eq(Interval::getName, intervalStringMap.get(key))
				);
				if (Func.isEmpty(interval)) {
					throw new ServiceException(intervalStringMap.get(key) +"未添加至区间管理");
				}
				intervalIdMap.put(key, interval.getId());
			}
		}

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		List<ShiftTemplate> shiftTemplateList = new ArrayList<>();
		for (Map<Integer, Map<Integer, String>> item : list) {
			log.info(list.indexOf(item) + "");
			log.info(item.toString());
			if (list.indexOf(item) > 1) {
				Map<Integer, String> lineItem = item.get(list.indexOf(item));
				for (int key : lineItem.keySet()) {
					if (key > 0) {
						for (int idKey : intervalIdMap.keySet()) {
							if (key == idKey) {
								if (Func.isNotEmpty(lineItem.get(key))) {
									try {
										Long intervalId = intervalIdMap.get(idKey);
										Date date = formatter.parse(lineItem.get(key));
										Time startTime = new Time(date.getTime());
										// 如果已有的区间和发车时间存在 => 不继续添加数据
										if (Func.isEmpty(getOne(Wrappers.<ShiftTemplate>lambdaQuery()
												.eq(ShiftTemplate::getIntervalId, intervalId)
												.eq(ShiftTemplate::getStartTime, startTime)
										))) {
											shiftTemplateList.add(new ShiftTemplate() {{
												setIntervalId(intervalId);
												setStartTime(startTime);
												setCreateTime(new Date());
												setStatus(BladeConstant.DB_STATUS_NORMAL);
											}});
										}
									} catch (ParseException e) {
										e.printStackTrace();
									}
								}
							}
						}
					}
				}
			}
		}
		saveBatch(shiftTemplateList);
	}
}
