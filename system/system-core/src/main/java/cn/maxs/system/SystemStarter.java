package cn.maxs.system;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Marcel.Maxs
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({"cn.maxs.**"})
@MapperScan(basePackages = "cn.maxs.**.mapper")
@Slf4j
public class SystemStarter {
    public static void main(String[] args) {
        log.info("系统服务模块 starting...");
        System.setProperty("nacos.logging.default.config.enabled", "false");
        SpringApplication.run(SystemStarter.class, args);
        log.info("系统服务模块 started!");
    }
}
