package cn.maxs.system.controller;

import cn.maxs.common.entity.framework.RestResult;
import cn.maxs.common.entity.po.SysRole;
import cn.maxs.common.entity.po.SysRolePermission;
import cn.maxs.system.service.SysRolePermissionService;
import cn.maxs.system.service.SysRoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色Controller
 * 2024/5/15
 *
 * @author Marcel.Maxs
 */
@RestController
@RequestMapping("/role-permission")
public class RolePermissionController {

    @Resource
    private SysRolePermissionService rolePermissionService;

    /**
     * 新增/修改
     */
    @PostMapping("/batchSaveUpdate")
    public RestResult<Boolean> batchSaveUpdate(@RequestBody List<SysRolePermission> param) {
        return RestResult.ok(rolePermissionService.batchSaveUpdate(param));
    }
}
