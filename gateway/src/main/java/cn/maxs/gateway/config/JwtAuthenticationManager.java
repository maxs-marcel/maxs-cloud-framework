package cn.maxs.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 认证管理器
 * 2024/5/14
 * @author Marcel.Maxs
 */
@Slf4j
@Component
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {
    @Resource
    private TokenStore tokenStore;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(t -> t instanceof BearerTokenAuthenticationToken)
                .cast(BearerTokenAuthenticationToken.class)
                .map(BearerTokenAuthenticationToken::getToken)
                .flatMap((accessToken -> {
                    log.info("accessToken: {}", accessToken);
                    OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
                    if(oAuth2AccessToken == null){
                        return Mono.error(new InvalidTokenException("无效的访问令牌，请检查。"));
                    }else if(oAuth2AccessToken.isExpired()){
                        return Mono.error(new InvalidTokenException("访问令牌已过期，请重新获取。"));
                    }
                    OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
                    if(oAuth2Authentication == null){
                        return Mono.error(new InvalidTokenException("访问令牌无效！"));
                    }else{
                        return Mono.just(oAuth2Authentication);
                    }
                }))
                .cast(Authentication.class);
    }
}
