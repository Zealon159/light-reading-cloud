package cn.zealon.readingcloud.homepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"cn.zealon.readingcloud.book.feign", "cn.zealon.readingcloud.account.feign"})
@SpringBootApplication(scanBasePackages = {"cn.zealon.readingcloud.homepage", "cn.zealon.readingcloud.common", "cn.zealon.readingcloud.book.feign", "cn.zealon.readingcloud.account.feign"})
@EnableHystrix
@EnableHystrixDashboard
public class HomepageApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomepageApplication.class, args);
    }

}
