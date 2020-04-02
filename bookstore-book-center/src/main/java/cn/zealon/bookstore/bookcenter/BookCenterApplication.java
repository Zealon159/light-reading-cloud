package cn.zealon.bookstore.bookcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 图书资源中心
 * @author zealon
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication(scanBasePackages="cn.zealon.bookstore")
public class BookCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookCenterApplication.class, args);
    }

}
