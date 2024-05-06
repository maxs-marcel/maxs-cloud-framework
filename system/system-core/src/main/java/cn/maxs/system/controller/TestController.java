package cn.maxs.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcel.Maxs
 * @description
 * @date 2024/4/30
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/getMsg/{msg}")
    public String getMsg(@PathVariable("msg") String msg){
//        throw new RuntimeException("www");
        return "系统服务模块收到消息：" + msg;
    }

}
