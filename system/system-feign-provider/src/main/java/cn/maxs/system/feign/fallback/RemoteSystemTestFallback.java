package cn.maxs.system.feign.fallback;

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
    public String getMsg(String msg) {
        return "访问system失败了嗷";
    }
}
