package cn.maxs.gateway.mapper;

import cn.maxs.common.entity.po.SysPermission;
import cn.maxs.common.entity.po.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author maxiangshun
* @description 针对表【sys_role(系统-角色)】的数据库操作Mapper
* @createDate 2024-05-14 14:18:42
* @Entity cn.maxs.common.entity.po.SysRole
*/
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysPermission> listPermissionByRoleIds(@Param("roleIds") List<String> roleIds);
}




