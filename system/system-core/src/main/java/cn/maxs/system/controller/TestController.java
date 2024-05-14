package cn.maxs.system.controller;

import cn.maxs.common.entity.framework.RestResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Controller
 * @author Marcel.Maxs
 * 2024/4/30
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/getMsg/{msg}")
    public RestResult<String> getMsg(@PathVariable("msg") String msg){
        throw new RuntimeException("www");
//        return RestResult.ok("系统服务模块收到消息：" + msg);
    }

}
