package cn.maxs.system.service.impl;

import cn.maxs.common.entity.po.SysPermission;
import cn.maxs.common.entity.po.SysRolePermission;
import cn.maxs.system.mapper.SysRolePermissionMapper;
import cn.maxs.system.service.SysPermissionService;
import cn.maxs.system.service.SysRolePermissionService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static cn.maxs.common.constant.system.RedisKey.ROLE_PERMISSIONS;

/**
* @author maxiangshun
* @description 针对表【sys_role_permission(系统-角色-权限关系)】的数据库操作Service实现
* @createDate 2024-05-14 14:18:37
*/
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission>
    implements SysRolePermissionService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private SysPermissionService sysPermissionService;

    @Transactional(rollbackFor = Exception.class)
    public boolean batchSaveUpdate(Collection<SysRolePermission> entityList) {
        if(CollectionUtils.isEmpty(entityList)) {
            return false;
        }
        Long roleId = entityList.stream().findFirst().orElseThrow(() -> new RuntimeException("角色不可为空")).getRoleId();

        // 批量插入、更新
        boolean saveResult = super.saveOrUpdateBatch(entityList);

        // 查出角色下的最新权限
        LambdaQueryWrapper<SysRolePermission> condition = new LambdaQueryWrapper<>();
        condition.eq(SysRolePermission::getRoleId, roleId);
        List<Long> permissionIds = super.list(condition).stream()
                .map(SysRolePermission::getPermissionId)
                .collect(Collectors.toList());

        // 获取该角色下的权限集合对应的实体
        List<SysPermission> permissionList = sysPermissionService.listByIds(permissionIds);

        // 更新redis缓存
        if(!CollectionUtils.isEmpty(permissionList) && saveResult) {
            stringRedisTemplate.opsForValue().set(ROLE_PERMISSIONS + roleId, JSON.toJSONString(permissionList));
        }
        return saveResult;
    }
}




