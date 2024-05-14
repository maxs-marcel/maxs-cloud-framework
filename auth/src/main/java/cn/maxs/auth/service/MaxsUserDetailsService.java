package cn.maxs.auth.service;

import cn.maxs.common.entity.po.SysUserRole;
import cn.maxs.common.entity.po.SysUser;
import cn.maxs.common.enums.system.EnableEnum;
import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户身份验证实现类
 * 2024/5/9
 * @author Marcel.Maxs
 */
@Slf4j
@Component(value = "userDetailsService")
public class MaxsUserDetailsService implements UserDetailsService {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
     * 在使用 Spring Security 进行认证授权时，需要获取用户信息并对其进行认证授权操作。
     * 在进行用户认证时，Spring Security 会调用 loadUserByUsername 方法获取用户信息。
     * 该方法接受一个用户名作为参数，返回一个 UserDetails 接口的实现对象，该对象包含了用户的基本信息（如用户名、密码、角色等），
     * Spring Security 会根据该对象进行后续的认证授权操作。
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户基本信息
        LambdaQueryWrapper<SysUser> condition = new LambdaQueryWrapper<>();
        condition = condition.eq(SysUser::getUsername, username);
        SysUser userInfo = sysUserService.getOne(condition);
        if (userInfo == null) {
            log.error("用户名「{}」不存在", username);
            throw new UsernameNotFoundException(username);
        }
        if(EnableEnum.DISABLE.getCode().equals(userInfo.getUserState())){
            log.error("用户「{}」已被禁用", username);
            throw new UsernameNotFoundException(username);
        }

        // 根据用户id查询角色信息
        LambdaQueryWrapper<SysUserRole> urCondition = new LambdaQueryWrapper<>();
        urCondition = urCondition.eq(SysUserRole::getUserId, userInfo.getId());
        List<SysUserRole> userRoles = sysUserRoleService.list(urCondition);
        List<SimpleGrantedAuthority> authorities = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(userRoles)){
            userRoles.forEach(ur -> {
                authorities.add(
                        new SimpleGrantedAuthority(String.valueOf(ur.getId()))
                );
            });
        }

        return new User(userInfo.getUsername(), userInfo.getPassword(), authorities);
    }
}
