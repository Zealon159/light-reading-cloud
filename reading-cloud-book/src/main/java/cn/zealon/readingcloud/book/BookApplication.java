package cn.zealon.readingcloud.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 图书资源中心
 * @author zealon
 */
@EnableFeignClients
@SpringBootApplication(scanBasePackages={"cn.zealon.readingcloud.book", "cn.zealon.readingcloud.common"})
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

}
