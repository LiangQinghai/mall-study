package cn.liangqinghai.study.mall.auth.config;

import cn.liangqinghai.study.mall.common.config.BaseSwaggerConfig;
import cn.liangqinghai.study.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.auth.config</p>
 * <p>File name: SwaggerConfig</p>
 * <div>
 * <h3>Description: </h3>
 * swagger配置
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 16:39
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    protected SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("cn.liangqinghai.study.mall.auth.controller")
                .title("认证中心")
                .description("认证中心")
                .contactName("HelloWorld")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
