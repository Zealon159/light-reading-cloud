package cn.zealon.readingcloud.homepage.common.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * ES 三方客户端配置
 * @author: zealon
 * @since: 2020/5/29
 */
@Configuration
public class JestConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(JestConfig.class);

    @Value("${es.servers}")
    private String es_servers;

    @Bean
    public JestClient jestClient() {
        JestClientFactory factory = new JestClientFactory();
        String[] sers = es_servers.split(",");
        HttpClientConfig httpClientConfig =
                new HttpClientConfig.Builder(Arrays.asList(sers))
                        .multiThreaded(true)
                        .connTimeout(5000)
                        .readTimeout(3000)
                        .maxTotalConnection(10)
                        .defaultMaxTotalConnectionPerRoute(10)
                        .build();
        factory.setHttpClientConfig(httpClientConfig);
        LOGGER.info("es_servers:{}", es_servers);
        return factory.getObject();
    }
}