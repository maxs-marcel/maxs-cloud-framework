package cn.maxs.business.one.controller;

//import cn.maxs.business.one.feign.RemoteSystemTest;

import cn.maxs.common.entity.framework.RestResult;
import cn.maxs.common.exception.BusinessException;
import cn.maxs.system.feign.RemoteSystemTest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试Controller
 * 2024/4/30
 * @author Marcel.Maxs
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private RemoteSystemTest remoteSystemTest;

    @PostMapping("/sendMsg")
    public RestResult<String> sendMsg(@RequestParam("msgInfo") String msgInfo){
        // 使用OpenFeign的方式来访问
//        throw new RuntimeException("呵呵");
        return remoteSystemTest.getMsg(msgInfo);
    }
}
