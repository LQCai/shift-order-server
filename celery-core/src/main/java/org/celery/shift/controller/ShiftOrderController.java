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
import org.celery.shift.entity.ShiftOrder;
import org.celery.shift.vo.ShiftOrderVO;
import org.celery.shift.wrapper.ShiftOrderWrapper;
import org.celery.shift.service.IShiftOrderService;
import org.springblade.core.boot.ctrl.BladeController;

/**
 * 班车预约表 控制器
 *
 * @author Celery
 * @since 2021-12-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("shift/shift-order")
@Api(value = "班车预约表", tags = "班车预约表接口")
public class ShiftOrderController extends BladeController {

	private final IShiftOrderService shiftOrderService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入shiftOrder")
	public R<ShiftOrderVO> detail(ShiftOrder shiftOrder) {
		ShiftOrder detail = shiftOrderService.getOne(Condition.getQueryWrapper(shiftOrder));
		return R.data(ShiftOrderWrapper.build().entityVO(detail));
	}

	/**
	 * 分页 班车预约表
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入shiftOrder")
	public R<IPage<ShiftOrderVO>> list(ShiftOrder shiftOrder, Query query) {
		QueryWrapper<ShiftOrder> queryWrapper = Condition.getQueryWrapper(shiftOrder);
		if (shiftOrder.getShiftBindKey().equals("")) {
			shiftOrder.setShiftBindKey(null);
		}
		IPage<ShiftOrder> pages = shiftOrderService.page(Condition.getPage(query), queryWrapper);
		return R.data(ShiftOrderWrapper.build().pageVO(pages));
	}


	/**
	 * 自定义分页 班车预约表
	 */
	@GetMapping("/page")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入shiftOrder")
	public R<IPage<ShiftOrderVO>> page(ShiftOrderVO shiftOrder, Query query) {
		IPage<ShiftOrderVO> pages = shiftOrderService.selectShiftOrderPage(Condition.getPage(query), shiftOrder);
		return R.data(pages);
	}

	/**
	 * 新增 班车预约表
	 */
	@PostMapping("/save")
	@ApiOperationSupport(order = 4)
	@ApiOperation(value = "新增", notes = "传入shiftOrder")
	public R save(@Valid @RequestBody ShiftOrder shiftOrder) {
		return R.status(shiftOrderService.save(shiftOrder));
	}

	/**
	 * 修改 班车预约表
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入shiftOrder")
	public R update(@Valid @RequestBody ShiftOrder shiftOrder) {
		return R.status(shiftOrderService.updateById(shiftOrder));
	}

	/**
	 * 新增或修改 班车预约表
	 */
	@PostMapping("/submit")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "新增或修改", notes = "传入shiftOrder")
	public R submit(@Valid @RequestBody ShiftOrder shiftOrder) {
		return R.status(shiftOrderService.saveOrUpdate(shiftOrder));
	}

	
	/**
	 * 删除 班车预约表
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(shiftOrderService.deleteLogic(Func.toLongList(ids)));
	}

	
}
