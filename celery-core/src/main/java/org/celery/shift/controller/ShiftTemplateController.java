package org.celery.shift.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import io.swagger.annotations.Api;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

import javax.validation.Valid;

import org.celery.shift.excel.ShiftImportListener;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.celery.shift.entity.ShiftTemplate;
import org.celery.shift.vo.ShiftTemplateVO;
import org.celery.shift.wrapper.ShiftTemplateWrapper;
import org.celery.shift.service.IShiftTemplateService;
import org.springblade.core.boot.ctrl.BladeController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 班次模板表 控制器
 *
 * @author Celery
 * @since 2021-12-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("shift/shift-template")
@Api(value = "班次模板表", tags = "班次模板表接口")
public class ShiftTemplateController extends BladeController {

	private final IShiftTemplateService shiftTemplateService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入shiftTemplate")
	public R<ShiftTemplateVO> detail(ShiftTemplate shiftTemplate) {
		ShiftTemplate detail = shiftTemplateService.getOne(Condition.getQueryWrapper(shiftTemplate));
		return R.data(ShiftTemplateWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 班次模板表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入shiftTemplate")
	public R<IPage<ShiftTemplateVO>> list(ShiftTemplate shiftTemplate, Query query) {
		IPage<ShiftTemplate> pages = shiftTemplateService.page(Condition.getPage(query), Condition.getQueryWrapper(shiftTemplate));
		return R.data(ShiftTemplateWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 班次模板表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入shiftTemplate")
	public R<IPage<ShiftTemplateVO>> page(ShiftTemplateVO shiftTemplate, Query query) {
		IPage<ShiftTemplateVO> pages = shiftTemplateService.selectShiftTemplatePage(Condition.getPage(query), shiftTemplate);
		return R.data(pages);
	}

	/**
	 * 新增 班次模板表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入shiftTemplate")
	public R save(@Valid @RequestBody ShiftTemplate shiftTemplate) {
		return R.status(shiftTemplateService.save(shiftTemplate));
	}

	/**
	 * 修改 班次模板表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入shiftTemplate")
	public R update(@Valid @RequestBody ShiftTemplate shiftTemplate) {
		return R.status(shiftTemplateService.updateById(shiftTemplate));
	}

	/**
	 * 新增或修改 班次模板表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入shiftTemplate")
	public R submit(@Valid @RequestBody ShiftTemplate shiftTemplate) {
		return R.status(shiftTemplateService.saveOrUpdate(shiftTemplate));
	}

	
	/**
	 * 删除 班次模板表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(shiftTemplateService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 导入班次
	 */
	@PostMapping("import-shift")
	@ApiOperationSupport(order = 8)
	@ApiOperation(value = "导入班次", notes = "传入excel")
	public R importShift(MultipartFile file) {
		String filename = file.getOriginalFilename();
		if (StringUtils.isEmpty(filename)) {
			throw new RuntimeException("请上传文件!");
		}
		if ((!StringUtils.endsWithIgnoreCase(filename, ".xls") && !StringUtils.endsWithIgnoreCase(filename, ".xlsx"))) {
			throw new RuntimeException("请上传正确的excel文件!");
		}
		InputStream inputStream;
		try {
			ShiftImportListener importListener = new ShiftImportListener(shiftTemplateService);
			inputStream = new BufferedInputStream(file.getInputStream());
			ExcelReaderBuilder builder = EasyExcel.read(inputStream, importListener);
			builder.doReadAll();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return R.success("操作成功");
	}
	
}
