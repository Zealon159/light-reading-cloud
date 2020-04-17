package cn.zealon.readingcloud.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"cn.zealon.readingcloud.book.feign"})
@EnableEurekaClient
@SpringBootApplication(scanBasePackages={ "cn.zealon.readingcloud.account", "cn.zealon.readingcloud.common", "cn.zealon.readingcloud.book.feign"})
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}
