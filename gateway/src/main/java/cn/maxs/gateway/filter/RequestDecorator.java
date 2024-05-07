package cn.maxs.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;

/**
 * 请求体装饰类
 */
@Slf4j
public class RequestDecorator extends ServerHttpRequestDecorator {
    private final DataBufferFactory bufferFactory;

    public RequestDecorator(ServerHttpRequest delegate, ServerWebExchange exchange) {
        super(delegate);
        this.bufferFactory = exchange.getResponse().bufferFactory();
    }

    @Override
    public Flux<DataBuffer> getBody() {
        Flux<DataBuffer> originalBody = super.getBody();
        return originalBody.map(dataBuffer -> {
            byte[] content = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(content);
            // 请求体数据
            String requestBody = new String(content, StandardCharsets.UTF_8);
            log.info("Request Body: " + requestBody);
            //重新包装了DataBuffer，所以要释放原始的DataBuffer
            DataBufferUtils.release(dataBuffer);
            // 返回新的DataBuffer
            return bufferFactory.wrap(content);
        });
    }
}