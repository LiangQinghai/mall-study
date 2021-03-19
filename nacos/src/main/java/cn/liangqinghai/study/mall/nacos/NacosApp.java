package cn.liangqinghai.study.mall.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.nacos</p>
 * <p>File name: NacosApp</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/18 16:11
 */
@SpringBootApplication(scanBasePackages = "com.alibaba.nacos")
@ServletComponentScan
@EnableScheduling
public class NacosApp {

    public static void main(String[] args) {

        System.setProperty("nacos.standalone", "true");
        System.setProperty("derby.stream.error.file",".derby.log");
        SpringApplication.run(NacosApp.class, args);

    }

}
