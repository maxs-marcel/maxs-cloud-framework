package cn.maxs.business.one;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Marcel.Maxs
 * @description
 * @date 2024/4/30
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
@EnableFeignClients({"cn.maxs.**"})
@ComponentScan(basePackages = "cn.maxs.**")
@Slf4j
public class BusinessOneStarter {

    public static void main(String[] args) {
        log.info("业务1服务模块 starting...");
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(BusinessOneStarter.class, args);
        log.info("业务1服务模块 started!");
    }
}
