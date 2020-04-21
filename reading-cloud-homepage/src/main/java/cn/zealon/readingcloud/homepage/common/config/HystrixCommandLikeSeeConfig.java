package cn.zealon.readingcloud.homepage.common.config;

import cn.zealon.readingcloud.account.feign.client.LikeSeeClient;
import com.netflix.hystrix.*;
import feign.Feign;
import feign.hystrix.HystrixFeign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 账户中心 - 喜欢看服务熔断配置
 * @author: zealon
 * @since: 2019/9/25

@Configuration
public class HystrixCommandLikeSeeConfig {

    @Bean
    public Feign.Builder likeSeeFeignHystrixBuilder() {
        return HystrixFeign.builder().setterFactory((target, method) -> HystrixCommand.Setter
            // 组
            .withGroupKey(HystrixCommandGroupKey.Factory.asKey(LikeSeeClient.class.getSimpleName()))
            .andCommandKey(HystrixCommandKey.Factory.asKey(LikeSeeClient.class.getSimpleName()))
            .andCommandPropertiesDefaults(
                // 超时配置
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)
            )
            .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                    .withAllowMaximumSizeToDivergeFromCoreSize(true)
                    .withMaximumSize(3)
                    .withCoreSize(2)
                    .withMaxQueueSize(20)
            ));
    }
}
 */