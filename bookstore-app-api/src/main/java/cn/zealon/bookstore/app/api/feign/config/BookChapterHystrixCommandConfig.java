package cn.zealon.bookstore.app.api.feign.config;

import com.netflix.hystrix.*;
import feign.Feign;
import feign.hystrix.HystrixFeign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: tangyl
 * @since: 2019/9/25

@Configuration
public class BookChapterHystrixCommandConfig {

    @Bean
    public Feign.Builder bookChapterFeignHystrixBuilder() {
        return HystrixFeign.builder().setterFactory((target, method) -> HystrixCommand.Setter

                // 组
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(BookChapterFeignClient.class.getSimpleName()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(BookChapterFeignClient.class.getSimpleName()))
                .andCommandPropertiesDefaults(
                    // 超时配置
                    HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000)


                )
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(3)
                        .withMaxQueueSize(333)
                ));
    }
}
 */