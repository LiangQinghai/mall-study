package cn.liangqinghai.study.mall.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.gateway.config</p>
 * <p>File name: SwaggerResourceConfig</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 11:12
 */
@Slf4j
@Primary
@Component
public class SwaggerResourceConfig implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;

    public SwaggerResourceConfig(RouteLocator routeLocator,
                                 GatewayProperties gatewayProperties) {
        this.routeLocator = routeLocator;
        this.gatewayProperties = gatewayProperties;
    }

    @Override
    public List<SwaggerResource> get() {

        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();

        // 路由id
        routeLocator.getRoutes()
                .subscribe(route -> routes.add(route.getId()));

        gatewayProperties.getRoutes()
                .stream()
                .filter(routeDefinition -> routes.contains(routeDefinition.getId()))
                .forEach(route ->
                        route.getPredicates()
                                .stream()
                                .filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
                                .forEach(predicateDefinition ->
                                        resources.add(
                                                swaggerResource(
                                                        route.getId(),
                                                        predicateDefinition
                                                                .getArgs()
                                                                .get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                                                .replace("**", "v2/api-docs")
                                                )
                                        )
                                )
                );

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {

        log.info("name: {}, location: {}", name, location);
        SwaggerResource resource = new SwaggerResource();
        resource.setName(name);
        resource.setLocation(location);
        resource.setSwaggerVersion("2.0");
        return resource;

    }

}
