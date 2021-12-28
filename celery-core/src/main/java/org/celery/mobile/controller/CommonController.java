package org.celery.mobile.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.DigestUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.system.entity.Param;
import org.springblade.modules.system.entity.Role;
import org.springblade.modules.system.entity.User;
import org.springblade.modules.system.enums.UserStatusEnum;
import org.springblade.modules.system.service.IParamService;
import org.springblade.modules.system.service.IRoleService;
import org.springblade.modules.system.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create on 2021-12-28
 *
 * @author Celery <1031868928@qq.com>
 */
@RestController
@AllArgsConstructor
@RequestMapping("common")
@Api(value = "预约", tags = "预约接口")
public class CommonController {

    private final IUserService userService;
    private final IRoleService roleService;
    private final IParamService paramService;

    /**
     * 注册申请
     */
    @PostMapping("/register")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "注册申请")
    public R<Boolean> register(@Valid @RequestBody User user) {
        user.setPassword(DigestUtil.encrypt(user.getPassword()));
        user.setRealName(user.getName());

        User checkUser = userService.getOne(Wrappers.<User>lambdaQuery()
                .eq(User::getCode, user.getCode())
        );
        if (Func.isNotEmpty(checkUser) && checkUser.getStatus().equals(UserStatusEnum.NORMAL.getStatus())) {
            throw new ServiceException("工号已注册!");
        }
        if (Func.isNotEmpty(checkUser) && checkUser.getStatus().equals(UserStatusEnum.APPLYING.getStatus())) {
            throw new ServiceException("申请中，请勿重复提交!");
        }

        Role role = roleService.getOne(Wrappers.<Role>lambdaQuery()
                .eq(Role::getRoleAlias, user.getRoleId())
        );
        if (Func.isEmpty(role)) {
            throw new ServiceException("后台配置错误, 请联系管理员");
        }

        user.setAccount(user.getCode());
        user.setRoleId(String.valueOf(role.getId()));
        user.setStatus(UserStatusEnum.APPLYING.getStatus());

        return R.status(userService.save(user));
    }

    /**
     * 获取班车时刻表
     */
    @GetMapping("/shift-table")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "获取班车时刻表")
    public R<String> getShiftTable() {
        Param param = paramService.getOne(Wrappers.<Param>lambdaQuery()
                .eq(Param::getParamKey, "shift.order.table")
        );
        if (Func.isEmpty(param)) {
            throw new ServiceException("后台配置错误，请联系管理员");
        }
        return R.data(param.getParamValue());
    }
}
