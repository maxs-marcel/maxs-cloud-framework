package cn.maxs.gateway;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Marcel.Maxs
 * @description
 * @date 2024/4/30
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"cn.maxs.**"})
@MapperScan(basePackages = "cn.maxs.**.mapper")
@Slf4j
public class GatewayStarter {

    public static void main(String[] args) {
        log.info("网关服务模块 starting...");
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(GatewayStarter.class, args);
        log.info("网关服务模块 started!");
    }
}
