package cn.maxs.gateway.filter;

import cn.maxs.common.entity.framework.RestResult;
import cn.maxs.common.enums.ResultStatus;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;

import java.nio.charset.StandardCharsets;

/**
 * 权限不足响应体处理类
 * 2024/5/14
 * @author Marcel.Maxs
 */
@Component
public class AccessDeniedHandler implements ServerAccessDeniedHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, AccessDeniedException e) {
        RestResult<Object> result = RestResult.fail(ResultStatus.ERR_403.getCode(), ResultStatus.ERR_403.getMsg());
        String params = JSON.toJSONString(result);

        ServerHttpResponse response = serverWebExchange.getResponse();

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        Mono<Void> ret;
        ret = response.writeAndFlushWith(Flux
                .just(ByteBufFlux
                        .just(response.bufferFactory().wrap(
                                params.getBytes(StandardCharsets.UTF_8))
                        )
                )
        );
        return ret;
    }
}