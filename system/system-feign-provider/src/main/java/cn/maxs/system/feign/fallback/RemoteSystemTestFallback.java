package cn.maxs.system.feign.fallback;

import cn.maxs.common.entity.framework.RestResult;
import cn.maxs.system.feign.RemoteSystemTest;
import org.springframework.stereotype.Component;

/**
 * @author Marcel.Maxs
 * @description
 * @date 2024/4/30
 */
@Component
public class RemoteSystemTestFallback implements RemoteSystemTest {
    @Override
    public RestResult<String> getMsg(String msg) {
        return RestResult.fail("访问失败了嗷，可能system服务没启动");
    }
}
