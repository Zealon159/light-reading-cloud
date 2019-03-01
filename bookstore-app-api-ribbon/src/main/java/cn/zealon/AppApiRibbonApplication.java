package cn.zealon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * ribbon 消费应用，提供接口
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AppApiRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApiRibbonApplication.class, args);
	}

	/**
	 * 实例化RestTemplate，通过@LoadBalanced注解开启均衡负载能力.
	 * @return restTemplate
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
