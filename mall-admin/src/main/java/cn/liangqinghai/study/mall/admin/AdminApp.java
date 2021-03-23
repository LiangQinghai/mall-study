package cn.liangqinghai.study.mall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.admin</p>
 * <p>File name: AdminApp</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/23 16:01
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AdminApp {

    public static void main(String[] args) {

        SpringApplication.run(AdminApp.class, args);

    }

}
