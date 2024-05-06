package cn.maxs.system.feign;

import cn.maxs.system.feign.fallback.RemoteSystemTestFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Marcel.Maxs
 * @description
 * @date 2024/4/30
 */
@FeignClient(value = "maxs-system", fallback = RemoteSystemTestFallback.class)
public interface RemoteSystemTest {
    @GetMapping("/test/getMsg/{msg}")
    String getMsg(@PathVariable(value = "msg") String msg);
}
