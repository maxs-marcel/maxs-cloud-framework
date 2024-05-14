package cn.maxs.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.maxs.common.entity.po.SysUser;
import cn.maxs.auth.service.SysUserService;
import cn.maxs.auth.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author maxiangshun
* @description 针对表【sys_user(系统-用户)】的数据库操作Service实现
* @createDate 2024-05-14 13:58:47
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




