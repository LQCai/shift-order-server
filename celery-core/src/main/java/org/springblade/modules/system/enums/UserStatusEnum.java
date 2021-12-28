package org.springblade.modules.system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Create on 2021-12-28
 *
 * @author Celery <1031868928@qq.com>
 */
@Getter
@AllArgsConstructor
public enum UserStatusEnum {

	APPLYING("申请中", 0),

	NORMAL("正常", 1),

	REFUSE("拒绝", 2),
	;

	final String name;
	final int status;

	public static String getName(int status) {
		if (status == NORMAL.getStatus()) {
			return NORMAL.getName();
		} else if (status == REFUSE.getStatus()) {
			return REFUSE.getName();
		} else if (status == APPLYING.getStatus()) {
			return APPLYING.getName();
		} else {
			return "";
		}
	}
}
