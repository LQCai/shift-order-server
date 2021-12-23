package org.celery.shift.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.celery.shift.service.IShiftTemplateService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserImportListener
 *
 * @author Chill
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class ShiftImportListener extends AnalysisEventListener<Map<Integer, String>> {

	/**
	 * 默认每隔3000条存储数据库
	 */
	private int batchCount = 3000;
	/**
	 * 缓存的数据列表
	 */
	private List<Map<Integer, Map<Integer, String>>> list;
	/**
	 * 用户service
	 */
	private final IShiftTemplateService shiftTemplateService;

	public ShiftImportListener(IShiftTemplateService shiftTemplateService) {
		this.shiftTemplateService = shiftTemplateService;
		this.list = new ArrayList<>();
	}

	@Override
	public void invoke(Map<Integer, String> data, AnalysisContext context) {
		log.info("解析到一条数据：{}, currentRowIndex: {}----", data.toString(), context.readRowHolder().getRowIndex());
		Map<Integer, Map<Integer, String>> map = new HashMap<>();
		map.put(context.readRowHolder().getRowIndex(), data);
		list.add(map);
		// 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
		if (list.size() >= batchCount) {
			saveData();
			// 存储完成清理 list
			list.clear();
		}
	}

	@Override
	public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
		log.info("解析到一条头数据：{}, currentRowHolder: {}", headMap.toString(), context.readRowHolder().getRowIndex());
		Map<Integer, Map<Integer, String>> map = new HashMap<>();
		map.put(context.readRowHolder().getRowIndex(), headMap);
		list.add(map);
	}

	/**
	 * 加上存储数据库
	 */
	private void saveData() {
		log.info("{}条数据，开始存储数据库！", list.size());
		shiftTemplateService.importShift(list);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext analysisContext) {
		// 调用importer方法
		shiftTemplateService.importShift(list);
		// 存储完成清理list
		list.clear();
	}
}
