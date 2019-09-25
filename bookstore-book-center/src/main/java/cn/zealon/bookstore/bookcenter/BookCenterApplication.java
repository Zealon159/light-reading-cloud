package cn.zealon.bookstore.bookcenter;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 图书中心
 * @author zealon
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class BookCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookCenterApplication.class, args);
    }

}
