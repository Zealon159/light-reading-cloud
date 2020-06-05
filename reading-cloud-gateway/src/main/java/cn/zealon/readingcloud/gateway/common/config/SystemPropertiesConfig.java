package cn.zealon.readingcloud.gateway.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 系统属性文件配置
 * @author: zealon
 * @since: 2020/6/5
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "system.properties")
public class SystemPropertiesConfig {

    /** 请求白名单 */
    private String whitelist;
}
