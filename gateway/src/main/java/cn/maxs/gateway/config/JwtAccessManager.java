package cn.maxs.gateway.config;

import cn.hutool.core.collection.ConcurrentHashSet;
import cn.maxs.common.entity.po.SysPermission;
import cn.maxs.gateway.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义鉴权管理器
 * 2024/5/14
 * @author Marcel.Maxs
 */
@Slf4j
@Component
public class JwtAccessManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private Set<String> permitAll = new ConcurrentHashSet<>();
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Resource
    private SysRoleService sysRoleService;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        ServerWebExchange exchange = authorizationContext.getExchange();
        ServerHttpRequest request = exchange.getRequest();

        // 跨域预检直接放行
        if(request.getMethod() == HttpMethod.OPTIONS){
            return Mono.just(new AuthorizationDecision(true));
        }
        // 获取请求资源
        String requestPath = request.getURI().getPath();
        if(permitAll(requestPath)){
            return Mono.just(new AuthorizationDecision(true));
        }
        // 权限校验
        return authentication
                .map(auth -> new AuthorizationDecision(checkAuthorities(auth, requestPath)))
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

    /**
     * 校验是不是可以直接放行的资源
     */
    private boolean permitAll(String requestPath){
        return permitAll.stream().anyMatch(r -> ANT_PATH_MATCHER.match(r, requestPath));
    }

    /**
     * 权限校验
     */
    private boolean checkAuthorities(Authentication auth, String requestPath){
        if(auth instanceof OAuth2Authentication){
            OAuth2Authentication authentication = (OAuth2Authentication) auth;
            String clientId = authentication.getOAuth2Request().getClientId();
            log.info("clientId: {}", clientId);
        }
        Object principal = auth.getPrincipal();
        log.info("principal: {}", principal);
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        log.info("requestPath: {}", requestPath);
        log.info("authorities: {}", authorities);

        List<String> roleIds = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // 根据角色ids，获取权限集合
        List<SysPermission> permissionList = sysRoleService.listPermissionByRoleIds(roleIds);

        for (SysPermission permission : permissionList) {
            String path = permission.getAccessPath();
            if(path != null && path.endsWith("/**")){
                path = path.substring(0, path.length() - 4);
                if(requestPath.startsWith(path)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
        return ReactiveAuthorizationManager.super.verify(authentication, object);
    }
}