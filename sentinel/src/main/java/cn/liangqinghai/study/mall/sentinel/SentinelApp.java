package cn.liangqinghai.study.mall.sentinel;

import com.alibaba.csp.sentinel.init.InitExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.sentinel</p>
 * <p>File name: SentinelApp</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/18 17:04
 */
@SpringBootApplication(scanBasePackages = "com.alibaba.csp.sentinel.dashboard")
public class SentinelApp {

    public static void main(String[] args) {

        new Thread(InitExecutor::doInit).start();
        SpringApplication.run(SentinelApp.class, args);

    }

}
