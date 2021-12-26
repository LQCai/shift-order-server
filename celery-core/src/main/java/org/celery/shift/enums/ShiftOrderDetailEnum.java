package org.celery.shift.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create on 2021-12-25
 *
 * @author Celery <1031868928@qq.com>
 */
@Getter
@AllArgsConstructor
public enum ShiftOrderDetailEnum {

	NORMAL("正常", 1),

	CANCEL("取消", 2),
	;

	final String name;
	final int status;

	public static String getName(int status) {
		if (status == NORMAL.getStatus()) {
			return NORMAL.getName();
		} else if (status == CANCEL.getStatus()) {
			return CANCEL.getName();
		} else {
			return "";
		}
	}
}
