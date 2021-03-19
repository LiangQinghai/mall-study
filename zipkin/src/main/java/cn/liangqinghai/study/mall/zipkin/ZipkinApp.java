package cn.liangqinghai.study.mall.zipkin;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import zipkin2.server.internal.EnableZipkinServer;
import zipkin2.server.internal.ZipkinActuatorImporter;
import zipkin2.server.internal.ZipkinModuleImporter;
import zipkin2.server.internal.banner.ZipkinBanner;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.zipkin</p>
 * <p>File name: ZipkinApp</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/18 17:15
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableZipkinServer
public class ZipkinApp {

    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    public static void main(String[] args) {

        new SpringApplicationBuilder(ZipkinApp.class)
                .banner(new ZipkinBanner())
                .initializers(new ZipkinModuleImporter(), new ZipkinActuatorImporter())
                .properties(EnableAutoConfiguration.ENABLED_OVERRIDE_PROPERTY + "=false")
                .run(args);

    }

}
