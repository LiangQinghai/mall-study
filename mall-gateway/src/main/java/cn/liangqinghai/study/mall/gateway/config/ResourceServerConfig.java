package cn.liangqinghai.study.mall.gateway.config;

import cn.hutool.core.util.ArrayUtil;
import cn.liangqinghai.study.mall.gateway.filter.WhiteListFilter;
import cn.liangqinghai.study.mall.gateway.security.AuthorizationManager;
import cn.liangqinghai.study.mall.gateway.security.RestAuthenticationEntryPoint;
import cn.liangqinghai.study.mall.gateway.security.RestfulAccessDeniedHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.gateway.config</p>
 * <p>File name: ResourceServerConfig</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 11:09
 */
@Slf4j
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity,
                                                         AuthorizationManager authorizationManager,
                                                         IgnoreUrlsConfig ignoreUrlsConfig,
                                                         WhiteListFilter whiteListFilter,
                                                         RestAuthenticationEntryPoint restAuthenticationEntryPoint,
                                                         RestfulAccessDeniedHandler restfulAccessDeniedHandler) {

        httpSecurity.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());

        httpSecurity.oauth2ResourceServer()
                .authenticationEntryPoint(restAuthenticationEntryPoint);

        httpSecurity.addFilterBefore(whiteListFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        httpSecurity.authorizeExchange()
                .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(), String.class)).permitAll()
                .anyExchange().access(authorizationManager)
                .and().exceptionHandling().accessDeniedHandler(restfulAccessDeniedHandler).authenticationEntryPoint(restAuthenticationEntryPoint)
                .and().csrf().disable();

        return httpSecurity.build();

    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthorityPrefix("ROLE_");
        converter.setAuthoritiesClaimName("authorities");

        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(converter);

        return new ReactiveJwtAuthenticationConverterAdapter(authenticationConverter);

    }

}
