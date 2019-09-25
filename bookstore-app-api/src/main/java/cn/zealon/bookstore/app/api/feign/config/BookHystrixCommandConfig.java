package cn.zealon.bookstore.app.api.feign.config;

import cn.zealon.bookstore.app.api.feign.BookFeignClient;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
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
    public Feign.Builder feignHystrixBuilder() {
        return HystrixFeign.builder().setterFactory((target, method) -> HystrixCommand.Setter
                // 组
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(BookFeignClient.class.getSimpleName()))
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
