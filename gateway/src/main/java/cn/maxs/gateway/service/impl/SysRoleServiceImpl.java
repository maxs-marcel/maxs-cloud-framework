package cn.maxs.gateway.service.impl;

import cn.maxs.common.entity.po.SysPermission;
import cn.maxs.common.entity.po.SysRole;
import cn.maxs.gateway.mapper.SysRoleMapper;
import cn.maxs.gateway.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
* @author maxiangshun
* @description 针对表【sys_role(系统-角色)】的数据库操作Service实现
* @createDate 2024-05-14 14:18:42
*/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
    implements SysRoleService {

    /**
     * 根据角色ids，获取权限集合
     */
    @Override
    public List<SysPermission> listPermissionByRoleIds(List<String> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)){
            return new ArrayList<>();
        }
        return baseMapper.listPermissionByRoleIds(roleIds);
    }
}




