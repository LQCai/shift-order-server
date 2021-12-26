package org.celery.mobile.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.celery.mobile.service.OrderService;
import org.celery.shift.entity.Interval;
import org.celery.shift.entity.ShiftOrderDetail;
import org.celery.shift.entity.ShiftTemplate;
import org.celery.shift.enums.ShiftOrderDetailEnum;
import org.celery.shift.service.IIntervalService;
import org.celery.shift.service.IShiftOrderDetailService;
import org.celery.shift.service.IShiftTemplateService;
import org.celery.shift.vo.IntervalVO;
import org.celery.shift.vo.ShiftOrderDetailVO;
import org.celery.shift.vo.ShiftTemplateVO;
import org.celery.shift.wrapper.IntervalWrapper;
import org.celery.shift.wrapper.ShiftOrderDetailWrapper;
import org.celery.shift.wrapper.ShiftTemplateWrapper;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.BladeUser;
import org.springblade.core.tool.api.R;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Create on 2021-12-25
 *
 * @author Celery <1031868928@qq.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("order")
@Api(value = "预约", tags = "预约接口")
public class OrderController {

    private final IIntervalService intervalService;
    private final IShiftTemplateService shiftTemplateService;
    private final OrderService orderService;
    private final IShiftOrderDetailService shiftOrderDetailService;

    /**
     * 区间列表
     */
    @GetMapping("/interval-list")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "分页", notes = "传入interval")
    public R<List<IntervalVO>> intervalList() {
        List<Interval> list = intervalService.list();
        return R.data(IntervalWrapper.build().listVO(list));
    }

    /**
     * 班次列表
     */
    @GetMapping("/shift-list")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "分页", notes = "传入interval")
    public R<List<ShiftTemplateVO>> shiftList(Long intervalId) {
        List<ShiftTemplate> list = shiftTemplateService.list(Wrappers.<ShiftTemplate>lambdaQuery()
                .eq(ShiftTemplate::getIntervalId, intervalId)
        );
        return R.data(ShiftTemplateWrapper.build().listVO(list));
    }

    /**
     * 预约提交
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "预约提交")
    public R<Boolean> submit(
            @ApiParam(value = "班次模板id", required = true) Long shiftTemplateId,
            @ApiParam(value = "预约日期", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @ApiParam(value = "预约备注", required = true) String remark,
            BladeUser bladeUser
    ) {
        return R.status(orderService.submit(shiftTemplateId, date, remark, bladeUser.getUserId()));
    }

    /**
     * 班车预约列表
     */
    @GetMapping("/record-list")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "班车预约列表", notes = "传入shiftOrderDetail")
    public R<IPage<ShiftOrderDetailVO>> recordList(ShiftOrderDetail shiftOrderDetail, Query query, BladeUser bladeUser) {
        QueryWrapper<ShiftOrderDetail> queryWrapper = Condition.getQueryWrapper(shiftOrderDetail);
        queryWrapper.lambda().eq(ShiftOrderDetail::getOrderUserId, bladeUser.getUserId());
        queryWrapper.lambda().eq(ShiftOrderDetail::getStatus, ShiftOrderDetailEnum.NORMAL.getStatus());
        queryWrapper.lambda().orderByDesc(ShiftOrderDetail::getCreateTime);
        IPage<ShiftOrderDetail> pages = shiftOrderDetailService.page(Condition.getPage(query), queryWrapper);
        return R.data(ShiftOrderDetailWrapper.build().pageVO(pages));
    }

    /**
     * 取消预约
     */
    @PostMapping("/cancel")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "取消预约")
    public R<Boolean> cancel(
            @ApiParam(value = "预约id", required = true) Long shiftOrderDetailId,
            BladeUser bladeUser
    ) {
        return R.status(orderService.cancel(shiftOrderDetailId, bladeUser.getUserId()));
    }
}
