package cn.zealon.readingcloud.homepage;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients(basePackages = {"cn.zealon.readingcloud.book.feign", "cn.zealon.readingcloud.account.feign"})
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"cn.zealon.readingcloud.homepage", "cn.zealon.readingcloud.common", "cn.zealon.readingcloud.book.feign", "cn.zealon.readingcloud.account.feign"})
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
