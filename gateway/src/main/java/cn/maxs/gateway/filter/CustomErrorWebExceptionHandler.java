package cn.maxs.gateway.filter;

import cn.maxs.common.entity.framework.RestResult;
import cn.maxs.common.enums.ResultStatus;
import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;

import java.nio.charset.StandardCharsets;

/**
 * 自定义web异常处理
 * 2024/5/15
 *
 * @author Marcel.Maxs
 */
@Order(-1)
@Configuration
public class CustomErrorWebExceptionHandler implements WebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        } else {
            RestResult<Object> result;
            if (ex instanceof InvalidTokenException) {
                // 针对访问令牌检查导致的异常，无法被InvalidHandler拦截，因此在更上层的WebExceptionHandler进行自定义处理。
                int httpErrorCode = ((InvalidTokenException) ex).getHttpErrorCode();
                // 直接取ex.getMessage()，message信息是在JwtAuthenticationManager自定义的。
                result = RestResult.fail(httpErrorCode, ex.getMessage());
                response.setStatusCode(HttpStatus.resolve(httpErrorCode));
            } else {
                // 其他未被正常拦截的异常，按500处理
                result = RestResult.fail(ResultStatus.ERR_500.getCode(), ex.getMessage());
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String params = JSON.toJSONString(result);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response.writeAndFlushWith(Flux
                    .just(ByteBufFlux
                            .just(response.bufferFactory().wrap(
                                    params.getBytes(StandardCharsets.UTF_8))
                            )
                    )
            );
        }
    }
}
