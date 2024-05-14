package cn.maxs.system.feign;

import cn.maxs.common.entity.framework.RestResult;
import cn.maxs.system.feign.fallback.RemoteSystemTestFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用 - 测试system模块feign调用
 * @author Marcel.Maxs
 * 2024/4/30
 */
@FeignClient(value = "maxs-system", fallback = RemoteSystemTestFallback.class)
public interface RemoteSystemTest {
    @GetMapping("/test/getMsg/{msg}")
    RestResult<String> getMsg(@PathVariable(value = "msg") String msg);
}
