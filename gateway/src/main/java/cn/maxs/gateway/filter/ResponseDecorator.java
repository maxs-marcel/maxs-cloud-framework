package cn.maxs.gateway.filter;

import cn.maxs.common.entity.framework.RestResult;
import cn.maxs.common.enums.ResultStatus;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 响应体装饰类
 */
@Slf4j
public class ResponseDecorator extends ServerHttpResponseDecorator {
    private final DataBufferFactory bufferFactory;

    public ResponseDecorator(ServerHttpResponse delegate) {
        super(delegate);
        this.bufferFactory = delegate.bufferFactory();
    }

    @Override
    public Mono<Void> writeWith(org.reactivestreams.Publisher<? extends DataBuffer> body) {
        HttpStatusCode statusCode = getStatusCode();
        if (statusCode != null && getStatusCode().is2xxSuccessful()) {
            // 正常情况直接返回
            return super.writeWith(body);
        } else if (body instanceof Flux) {
            // 异常情况返回固定格式
            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
            return super.writeWith(
                    fluxBody.buffer().map(dataBuffers -> {
                                DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                                DataBuffer join = dataBufferFactory.join(dataBuffers);
                                byte[] content = new byte[join.readableByteCount()];
                                join.read(content);
                                DataBufferUtils.release(join);
                                String responseBody = new String(content, StandardCharsets.UTF_8);
                                log.error("网关拦截异常响应体：response body: {}", responseBody);
                                RestResult<Object> fail = RestResult.fail(
                                        statusCode == null ? ResultStatus.FAIL.getCode() : statusCode.value(),
                                        "服务器喝假酒喝断片了～让它缓缓吧");

                                byte[] uppedContent = JSON.toJSONString(fail).getBytes(StandardCharsets.UTF_8);
                                return bufferFactory.wrap(uppedContent);
                            }
                    ));
        } else {
            return super.writeWith(body);
        }
    }
}