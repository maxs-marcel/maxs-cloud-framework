package cn.maxs.gateway.filter;

import cn.maxs.common.entity.framework.RestResult;
import cn.maxs.common.enums.ResultStatus;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.authentication.HttpBasicServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;

import java.nio.charset.StandardCharsets;

/**
 * 登录失效响应体处理类
 * 2024/5/14
 * @author Marcel.Maxs
 */
@Component
public class InvalidHandler extends HttpBasicServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        RestResult<Object> result = RestResult.fail(ResultStatus.ERR_401.getCode(), ResultStatus.ERR_401.getMsg());
        String params = JSON.toJSONString(result);

        ServerHttpResponse response = exchange.getResponse();

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.writeAndFlushWith(Flux
                .just(ByteBufFlux
                        .just(response.bufferFactory().wrap(
                                params.getBytes(StandardCharsets.UTF_8))
                        )
                )
        );
    }
}