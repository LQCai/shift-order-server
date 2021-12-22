package org.celery.shift.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import javax.validation.Valid;

import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.celery.shift.entity.Interval;
import org.celery.shift.vo.IntervalVO;
import org.celery.shift.wrapper.IntervalWrapper;
import org.celery.shift.service.IIntervalService;
import org.springblade.core.boot.ctrl.BladeController;

import java.util.List;

/**
 * 区间表 控制器
 *
 * @author Celery
 * @since 2021-12-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("shift/interval")
@Api(value = "区间表", tags = "区间表接口")
public class IntervalController extends BladeController {

	private final IIntervalService intervalService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入interval")
	public R<IntervalVO> detail(Interval interval) {
		Interval detail = intervalService.getOne(Condition.getQueryWrapper(interval));
		return R.data(IntervalWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 区间表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入interval")
	public R<IPage<IntervalVO>> list(Interval interval, Query query) {
		IPage<Interval> pages = intervalService.page(Condition.getPage(query), Condition.getQueryWrapper(interval));
		return R.data(IntervalWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 区间表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入interval")
	public R<IPage<IntervalVO>> page(IntervalVO interval, Query query) {
		IPage<IntervalVO> pages = intervalService.selectIntervalPage(Condition.getPage(query), interval);
		return R.data(pages);
	}

	/**
	 * 新增 区间表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入interval")
	public R save(@Valid @RequestBody Interval interval) {
		return R.status(intervalService.save(interval));
	}

	/**
	 * 修改 区间表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入interval")
	public R update(@Valid @RequestBody Interval interval) {
		return R.status(intervalService.updateById(interval));
	}

	/**
	 * 新增或修改 区间表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入interval")
	public R submit(@Valid @RequestBody Interval interval) {
		return R.status(intervalService.saveOrUpdate(interval));
	}

	
	/**
	 * 删除 区间表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(intervalService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 下拉数据源
	 */
	@GetMapping("/select")
	@ApiOperation(value = "下拉数据源", notes = "传入interval")
	public R<List<Interval>> select(Interval interval) {
		QueryWrapper<Interval> queryWrapper = Condition.getQueryWrapper(interval);
		List<Interval> list = intervalService.list(queryWrapper);
		return R.data(list);
	}
}
