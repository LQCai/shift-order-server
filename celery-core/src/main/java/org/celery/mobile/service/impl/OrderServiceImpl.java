package org.celery.mobile.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.celery.mobile.service.OrderService;
import org.celery.shift.entity.ShiftOrder;
import org.celery.shift.entity.ShiftOrderDetail;
import org.celery.shift.entity.ShiftTemplate;
import org.celery.shift.enums.ShiftOrderDetailEnum;
import org.celery.shift.service.IShiftOrderDetailService;
import org.celery.shift.service.IShiftOrderService;
import org.celery.shift.service.IShiftTemplateService;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


/**
 * Create on 2021-12-26
 *
 * @author Celery <1031868928@qq.com>
 */
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final IShiftTemplateService shiftTemplateService;
    private final IShiftOrderService shiftOrderService;
    private final IShiftOrderDetailService shiftOrderDetailService;

    @Override
    @Transactional
    public Boolean submit(Long shiftTemplateId, LocalDate date, String remark, Long userId) {
        ShiftTemplate shiftTemplate = shiftTemplateService.getOne(Wrappers.<ShiftTemplate>lambdaQuery()
                .eq(ShiftTemplate::getId, shiftTemplateId)
        );
            if (Func.isEmpty(shiftTemplate)) {
            throw new ServiceException("预约班次不存在");
        }

        if (Func.isNotEmpty(shiftOrderDetailService.getOne(Wrappers.<ShiftOrderDetail>lambdaQuery()
                .eq(ShiftOrderDetail::getShiftId, shiftTemplateId)
                .eq(ShiftOrderDetail::getOrderUserId, userId)
                .eq(ShiftOrderDetail::getStatus, ShiftOrderDetailEnum.NORMAL.getStatus())
        ))) {
            throw new ServiceException("请勿重复预约");
        }

        ShiftOrder shiftOrder = new ShiftOrder() {{
            setDate(date);
            setShiftId(shiftTemplateId);
            setStartTime(shiftTemplate.getStartTime());
            setIntervalId(shiftTemplate.getIntervalId());
        }};
        shiftOrderService.save(shiftOrder);

        ShiftOrderDetail shiftOrderDetail = new ShiftOrderDetail() {{
            setDate(date);
            setShiftOrderId(shiftOrder.getId());
            setShiftId(shiftTemplateId);
            setOrderUserId(userId);
            setIntervalId(shiftTemplate.getIntervalId());
            setRemark(remark);
            setStartTime(shiftTemplate.getStartTime());
        }};
        shiftOrderDetailService.save(shiftOrderDetail);

        return true;
    }

    @Override
    public Boolean cancel(Long shiftOrderDetailId, Long userId) {
        ShiftOrderDetail shiftOrderDetail = shiftOrderDetailService.getOne(Wrappers.<ShiftOrderDetail>lambdaQuery()
                .eq(ShiftOrderDetail::getId, shiftOrderDetailId)
                .eq(ShiftOrderDetail::getOrderUserId, userId)
                .eq(ShiftOrderDetail::getStatus, ShiftOrderDetailEnum.NORMAL.getStatus())
        );
        if (Func.isEmpty(shiftOrderDetail)) {
            throw new ServiceException("您尚未预约");
        }

        shiftOrderDetail.setStatus(ShiftOrderDetailEnum.CANCEL.getStatus());

        return shiftOrderDetailService.saveOrUpdate(shiftOrderDetail);
    }
}
