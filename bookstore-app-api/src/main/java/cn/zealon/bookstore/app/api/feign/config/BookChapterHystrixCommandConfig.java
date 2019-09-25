package cn.zealon.bookstore.app.api.feign.config;

import cn.zealon.bookstore.app.api.feign.BookChapterFeignClient;
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
public class BookChapterHystrixCommandConfig {

    @Bean
    public Feign.Builder feignHystrixBuilder2() {
        return HystrixFeign.builder().setterFactory((target, method) -> HystrixCommand.Setter
                // 组
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(BookChapterFeignClient.class.getSimpleName()))
                .andCommandPropertiesDefaults(
                    // 超时配置
                    HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1500)
                )
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()

                        .withCoreSize(3)
                        .withMaxQueueSize(333)
                ));
    }
}
