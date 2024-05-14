package cn.maxs.auth.service.impl;

import cn.maxs.auth.mapper.SysPermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.maxs.common.entity.po.SysPermission;
import cn.maxs.auth.service.SysPermissionService;
import org.springframework.stereotype.Service;

/**
* @author maxiangshun
* @description 针对表【sys_permission(系统-权限)】的数据库操作Service实现
* @createDate 2024-05-14 14:18:49
*/
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission>
    implements SysPermissionService{

}




