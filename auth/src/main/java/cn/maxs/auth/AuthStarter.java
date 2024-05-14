package cn.maxs.auth;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Marcel.Maxs
 * @description
 * @date 2024/4/30
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
@MapperScan(basePackages = "cn.maxs.**.mapper")
public class AuthStarter {
    public static void main(String[] args) {
        log.info("鉴权服务模块 starting...");
        SpringApplication.run(AuthStarter.class, args);
        log.info("鉴权服务模块 started!");
    }
}
