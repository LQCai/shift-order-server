package org.celery.shift.controller;

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
import org.celery.shift.entity.ShiftOrderDetail;
import org.celery.shift.vo.ShiftOrderDetailVO;
import org.celery.shift.wrapper.ShiftOrderDetailWrapper;
import org.celery.shift.service.IShiftOrderDetailService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 * 班车预约详情表 控制器
 *
 * @author Celery
 * @since 2021-12-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("shift/shift-order-detail")
@Api(value = "班车预约详情表", tags = "班车预约详情表接口")
public class ShiftOrderDetailController extends BladeController {

	private final IShiftOrderDetailService shiftOrderDetailService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入shiftOrderDetail")
	public R<ShiftOrderDetailVO> detail(ShiftOrderDetail shiftOrderDetail) {
		ShiftOrderDetail detail = shiftOrderDetailService.getOne(Condition.getQueryWrapper(shiftOrderDetail));
		return R.data(ShiftOrderDetailWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 班车预约详情表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入shiftOrderDetail")
	public R<IPage<ShiftOrderDetailVO>> list(ShiftOrderDetail shiftOrderDetail, Query query) {
		IPage<ShiftOrderDetail> pages = shiftOrderDetailService.page(Condition.getPage(query), Condition.getQueryWrapper(shiftOrderDetail));
		return R.data(ShiftOrderDetailWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 班车预约详情表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入shiftOrderDetail")
	public R<IPage<ShiftOrderDetailVO>> page(ShiftOrderDetailVO shiftOrderDetail, Query query) {
		IPage<ShiftOrderDetailVO> pages = shiftOrderDetailService.selectShiftOrderDetailPage(Condition.getPage(query), shiftOrderDetail);
		return R.data(pages);
	}

	/**
	 * 新增 班车预约详情表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入shiftOrderDetail")
	public R save(@Valid @RequestBody ShiftOrderDetail shiftOrderDetail) {
		return R.status(shiftOrderDetailService.save(shiftOrderDetail));
	}

	/**
	 * 修改 班车预约详情表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入shiftOrderDetail")
	public R update(@Valid @RequestBody ShiftOrderDetail shiftOrderDetail) {
		return R.status(shiftOrderDetailService.updateById(shiftOrderDetail));
	}

	/**
	 * 新增或修改 班车预约详情表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入shiftOrderDetail")
	public R submit(@Valid @RequestBody ShiftOrderDetail shiftOrderDetail) {
		return R.status(shiftOrderDetailService.saveOrUpdate(shiftOrderDetail));
	}

	
	/**
	 * 删除 班车预约详情表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(shiftOrderDetailService.deleteLogic(Func.toLongList(ids)));
	}

	
}
