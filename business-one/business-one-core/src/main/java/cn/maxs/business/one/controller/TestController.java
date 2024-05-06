package cn.maxs.business.one.controller;

//import cn.maxs.business.one.feign.RemoteSystemTest;

import cn.maxs.system.feign.RemoteSystemTest;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcel.Maxs
 * @description
 * @date 2024/4/30
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RemoteSystemTest remoteSystemTest;

    @PostMapping("/sendMsg")
    public String sendMsg(@RequestParam("msgInfo") String msgInfo){
        // 使用OpenFeign的方式来访问
        return remoteSystemTest.getMsg(msgInfo);
    }
}
