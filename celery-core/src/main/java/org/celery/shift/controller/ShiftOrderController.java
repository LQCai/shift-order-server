package org.celery.shift.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lombok.SneakyThrows;
import org.apache.commons.codec.Charsets;
import org.celery.shift.entity.Interval;
import org.celery.shift.entity.ShiftOrderDetail;
import org.celery.shift.enums.ShiftOrderDetailEnum;
import org.celery.shift.excel.ShiftOrderExcel;
import org.celery.shift.service.IIntervalService;
import org.celery.shift.service.IShiftOrderDetailService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.system.entity.User;
import org.springblade.modules.system.service.IUserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.celery.shift.entity.ShiftOrder;
import org.celery.shift.vo.ShiftOrderVO;
import org.celery.shift.wrapper.ShiftOrderWrapper;
import org.celery.shift.service.IShiftOrderService;
import org.springblade.core.boot.ctrl.BladeController;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URLEncoder;
import java.util.*;

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
	private final IShiftOrderDetailService shiftOrderDetailService;
	private final IUserService userService;
	private final IIntervalService intervalService;

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
		if (!Func.isNull(shiftOrder.getShiftBindKey()) && shiftOrder.getShiftBindKey().equals("")) {
			shiftOrder.setShiftBindKey(null);
		}
		queryWrapper.lambda().orderByDesc(ShiftOrder::getDate);
		queryWrapper.lambda().orderByAsc(ShiftOrder::getStartTime);
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

	/**
	 * 导出预约记录
	 */
	@SneakyThrows
	@GetMapping("export")
	@ApiOperationSupport(order = 13)
	@ApiOperation(value = "导出预约记录", notes = "传入user")
	public void export(@ApiIgnore @RequestParam Map<String, Object> shiftOrder, HttpServletResponse response) {
		QueryWrapper<ShiftOrder> queryWrapper = Condition.getQueryWrapper(shiftOrder, ShiftOrder.class);
		queryWrapper.lambda().orderByAsc(ShiftOrder::getStartTime);
		List<ShiftOrder> shiftOrderList = shiftOrderService.list(queryWrapper);

		if (Func.isEmpty(shiftOrder.get("date"))) {
			throw new ServiceException("请选择指定导出日期");
		}

		List<ShiftOrderDetail> shiftOrderDetailList = shiftOrderDetailService.list(Wrappers.<ShiftOrderDetail>lambdaQuery()
				.eq(ShiftOrderDetail::getDate, shiftOrder.get("date"))
				.eq(ShiftOrderDetail::getStatus, ShiftOrderDetailEnum.NORMAL.getStatus())
				.orderByAsc(ShiftOrderDetail::getCreateTime)
		);
		List<User> userList = userService.list();
		List<Interval> intervalList = intervalService.list();

		response.setContentType("application/vnd.ms-excel");
		response.setCharacterEncoding(Charsets.UTF_8.name());
		String fileName = URLEncoder.encode(shiftOrder.get("date").toString() + "-班车预约信息", Charsets.UTF_8.name());
		response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

		ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
		for (int i = 0; i < shiftOrderList.size(); i++) {
			List<ShiftOrderExcel> shiftOrderExcelList = new ArrayList<>();
			for (ShiftOrderDetail shiftOrderDetail : shiftOrderDetailList) {
				if (Objects.equals(shiftOrderDetail.getShiftOrderId(), shiftOrderList.get(i).getId())) {
					shiftOrderExcelList.add(new ShiftOrderExcel() {{
						for (User user : userList) {
							if (user.getId().equals(shiftOrderDetail.getOrderUserId())) {
								setCode(user.getCode());
								setRealName(user.getRealName());
								setPhone(user.getPhone());
							}
						}
						setRemark(shiftOrderDetail.getRemark());
						setCreateTime(DateUtil.formatDateTime(shiftOrderDetail.getCreateTime()));
					}});
				}
			}
			String intervalName = "";
			for (Interval interval : intervalList) {
				if (interval.getId().equals(shiftOrderList.get(i).getIntervalId())) {
					intervalName = interval.getName();
					break;
				}
			}
			WriteSheet writeSheet = EasyExcel.writerSheet(i, DateUtil.format(shiftOrderList.get(i).getStartTime(), "HHmm") + "_" + intervalName)
					.head(ShiftOrderExcel.class)
					.build();
			excelWriter.write(shiftOrderExcelList, writeSheet);
		}
		excelWriter.finish();
		response.flushBuffer();
	}
	
}
