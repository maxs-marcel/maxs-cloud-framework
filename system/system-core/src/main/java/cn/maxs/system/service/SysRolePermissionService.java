package cn.maxs.system.service;

import cn.maxs.common.entity.po.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;

/**
* @author maxiangshun
* @description 针对表【sys_role_permission(系统-角色-权限关系)】的数据库操作Service
* @createDate 2024-05-14 14:18:37
*/
public interface SysRolePermissionService extends IService<SysRolePermission> {

    boolean batchSaveUpdate(Collection<SysRolePermission> entityList);
}
