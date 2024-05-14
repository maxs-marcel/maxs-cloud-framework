package cn.maxs.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局响应体拦截
 */
@Slf4j
@Component
public class GlobalResponseFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 注入requestDecorator、responseDecorator用于获得请求体、响应体
        ServerHttpRequestDecorator requestDecorator = new RequestDecorator(exchange.getRequest(), exchange);
        ServerHttpResponseDecorator responseDecorator = new ResponseDecorator(exchange.getResponse());
        return chain.filter(exchange.mutate()
                        .request(requestDecorator).response(responseDecorator).build()
                )
                .then(Mono.just(exchange))
                .map(serverWebExchange -> serverWebExchange)
                .then();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
