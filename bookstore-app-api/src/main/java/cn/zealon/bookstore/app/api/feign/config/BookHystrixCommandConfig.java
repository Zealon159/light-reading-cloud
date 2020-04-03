package cn.zealon.bookstore.app.api.feign.config;

import cn.zealon.bookstore.bookcenter.feign.client.BookClient;
import com.netflix.hystrix.*;
import feign.Feign;
import feign.hystrix.HystrixFeign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: tangyl
 * @since: 2019/9/25
 */
@Configuration
public class BookHystrixCommandConfig {

    @Bean
    public Feign.Builder bookFeignHystrixBuilder() {
        return HystrixFeign.builder().setterFactory((target, method) -> HystrixCommand.Setter
                // 组
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(BookClient.class.getSimpleName()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(BookClient.class.getSimpleName()))
                .andCommandPropertiesDefaults(
                    // 超时配置
                    HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)
                )
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withAllowMaximumSizeToDivergeFromCoreSize(true)
                        .withMaximumSize(10)
                        .withCoreSize(5)
                        .withMaxQueueSize(55)
                ));
    }
}
