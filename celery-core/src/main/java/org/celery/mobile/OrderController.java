package org.celery.mobile;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.celery.shift.entity.Interval;
import org.celery.shift.entity.ShiftTemplate;
import org.celery.shift.service.IIntervalService;
import org.celery.shift.service.IShiftTemplateService;
import org.celery.shift.vo.IntervalVO;
import org.celery.shift.vo.ShiftTemplateVO;
import org.celery.shift.wrapper.IntervalWrapper;
import org.celery.shift.wrapper.ShiftTemplateWrapper;
import org.springblade.core.tool.api.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 区间列表
     */
    @GetMapping("/interval-list")
    @ApiOperationSupport(order = 2)
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
}
