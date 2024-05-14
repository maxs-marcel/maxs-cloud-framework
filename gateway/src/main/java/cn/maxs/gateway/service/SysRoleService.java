package cn.maxs.gateway.service;

import cn.maxs.common.entity.po.SysPermission;
import cn.maxs.common.entity.po.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author maxiangshun
* @description 针对表【sys_role(系统-角色)】的数据库操作Service
* @createDate 2024-05-14 14:18:42
*/
public interface SysRoleService extends IService<SysRole> {

    /**
     * 根据角色ids，获取权限集合
     */
    List<SysPermission> listPermissionByRoleIds(List<String> roleIds);
}
