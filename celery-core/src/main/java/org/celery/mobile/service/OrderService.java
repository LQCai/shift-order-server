package org.celery.mobile.service;

import java.time.LocalDate;

/**
 * Create on 2021-12-26
 *
 * @author Celery <1031868928@qq.com>
 */
public interface OrderService {
    Boolean submit(Long shiftTemplateId, LocalDate date, String remark, Long userId);

    Boolean cancel(Long shiftOrderDetailId, Long userId);
}
