package cn.zealon.readingcloud.homepage;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients(basePackages = {"cn.zealon.readingcloud.book.feign.client"})
@EnableEurekaClient
@SpringBootApplication(scanBasePackages="cn.zealon.readingcloud")
@EnableHystrix
@EnableHystrixDashboard
public class HomepageApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomepageApplication.class, args);
    }

    @Bean
    public Logger.Level feignLoggerLevel(){
        return  Logger.Level.FULL;
    }


}
