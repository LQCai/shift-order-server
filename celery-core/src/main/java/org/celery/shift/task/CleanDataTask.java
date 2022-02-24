package org.celery.shift.task;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.celery.shift.entity.ShiftOrder;
import org.celery.shift.entity.ShiftOrderDetail;
import org.celery.shift.service.IShiftOrderDetailService;
import org.celery.shift.service.IShiftOrderService;
import org.springblade.core.tool.utils.DateUtil;
import org.springblade.core.tool.utils.SpringUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create on 2022-01-05
 *
 * @author Celery <1031868928@qq.com>
 */
@Component
@Slf4j
public class CleanDataTask {
    @Scheduled(cron = "0 0 0 * * ?")
    public void task () {
        IShiftOrderService shiftOrderService = SpringUtil.getBean(IShiftOrderService.class);
        IShiftOrderDetailService shiftOrderDetailService = SpringUtil.getBean(IShiftOrderDetailService.class);

        // 获取上个月日期，用于删除上一月的预约数据
        Date lastMonth = DateUtil.plusMonths(DateUtil.now(), -1);

        List<ShiftOrder> shiftOrderList = shiftOrderService.list(Wrappers.<ShiftOrder>lambdaQuery()
                .lt(ShiftOrder::getDate, DateUtil.formatDate(lastMonth))
        );

        List<ShiftOrderDetail> shiftOrderDetailList = shiftOrderDetailService.list(Wrappers.<ShiftOrderDetail>lambdaQuery()
                .lt(ShiftOrderDetail::getDate, DateUtil.formatDate(lastMonth))
        );

        shiftOrderService.deleteByIds(shiftOrderList.stream().map(ShiftOrder::getId).collect(Collectors.toList()));
        shiftOrderDetailService.deleteByIds(shiftOrderDetailList.stream().map(ShiftOrderDetail::getId).collect(Collectors.toList()));
    }
}
