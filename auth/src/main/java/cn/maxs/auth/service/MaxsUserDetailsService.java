package cn.maxs.auth.service;

import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户身份验证实现类
 * 2024/5/9
 *
 * @author Marcel.Maxs
 */
@Slf4j
@Component(value = "userDetailsService")
public class MaxsUserDetailsService implements UserDetailsService {
    @Resource
    private PasswordEncoder passwordEncoder;

    // 临时测试
    private List<User> userList = Lists.newArrayList();
    @PostConstruct
    public void initUserList(){
        String password = passwordEncoder.encode("123456");
        List<SimpleGrantedAuthority> zhangSanAuthorities = new ArrayList<>();
        zhangSanAuthorities.add(new SimpleGrantedAuthority("SYSTEM"));
        zhangSanAuthorities.add(new SimpleGrantedAuthority("API"));
        List<SimpleGrantedAuthority> liSiAuthorities = new ArrayList<>();
        liSiAuthorities.add(new SimpleGrantedAuthority("/maxs-system/test/**"));
        userList.add(new User("zhangsan", password, zhangSanAuthorities));
        userList.add(new User("lisi", password, liSiAuthorities));
    }

    /**
     * 在使用 Spring Security 进行认证授权时，需要获取用户信息并对其进行认证授权操作。
     * 在进行用户认证时，Spring Security 会调用 loadUserByUsername 方法获取用户信息。
     * 该方法接受一个用户名作为参数，返回一个 UserDetails 接口的实现对象，该对象包含了用户的基本信息（如用户名、密码、角色等），
     * Spring Security 会根据该对象进行后续的认证授权操作。
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username is:{}", username);
        // TODO: 查询数据库操作
        User user = userList.stream()
                .filter(t -> t.getUsername().equals(username))
                .findFirst().orElseThrow(() -> new UsernameNotFoundException(username));
        return user;
    }
}
