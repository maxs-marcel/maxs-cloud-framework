package cn.maxs.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * 直接放行的资源配置
 * 2024/5/15
 *
 * @author Marcel.Maxs
 */
@Configuration
@Data
@ConfigurationProperties("maxs-security")
public class IgnoreUrlsConfig {
    private Set<String> ignoreUrls;
}
