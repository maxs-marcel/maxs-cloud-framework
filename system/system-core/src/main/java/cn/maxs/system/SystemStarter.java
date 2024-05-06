package cn.maxs.system;

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
@EnableFeignClients({"cn.maxs.**"})
@Slf4j
public class SystemStarter {
    public static void main(String[] args) {
        log.info("系统服务模块 starting...");
        SpringApplication.run(SystemStarter.class, args);
        log.info("系统服务模块 started!");
    }
}
