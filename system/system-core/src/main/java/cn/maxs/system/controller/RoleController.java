package cn.maxs.system.controller;

import cn.maxs.common.entity.framework.RestResult;
import cn.maxs.common.entity.po.SysRole;
import cn.maxs.system.service.SysRoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色Controller
 * 2024/5/15
 *
 * @author Marcel.Maxs
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private SysRoleService roleService;

    /**
     * 新增
     */
    @PostMapping("/save")
    public RestResult<Boolean> save(@RequestBody SysRole role) {
        return RestResult.ok(roleService.save(role));
    }
}
