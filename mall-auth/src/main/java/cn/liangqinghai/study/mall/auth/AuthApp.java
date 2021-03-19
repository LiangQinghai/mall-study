package cn.liangqinghai.study.mall.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.auth</p>
 * <p>File name: AuthApp</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 16:37
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AuthApp {

    public static void main(String[] args) {

        SpringApplication.run(AuthApp.class, args);

    }

}
