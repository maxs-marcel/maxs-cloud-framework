package cn.maxs.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

import javax.annotation.Resource;

/**
 * 2024/5/14
 *
 * @author Marcel.Maxs
 */
@Slf4j
@Configuration
@EnableWebFluxSecurity
public class WebFluxSecurityConfig {
    @Resource
    private JwtAuthenticationManager jwtAuthenticationManager;
    @Resource
    private JwtAccessManager jwtAccessManager;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        log.info("init WebFluxSecurity SecurityWebFilterChain Bean");
        // 认证过滤器
        AuthenticationWebFilter authenticationWebFilter = new AuthenticationWebFilter(jwtAuthenticationManager);
        authenticationWebFilter.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());

        return http
                .httpBasic().disable()
                .csrf().disable()
                .authorizeExchange()
                // 鉴权
                .pathMatchers("/maxs-auth/oauth/**").permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().access(jwtAccessManager)
                .and()
                .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }
}
