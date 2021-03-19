package cn.liangqinghai.study.mall.gateway.filter;

import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.gateway.filter</p>
 * <p>File name: AuthFilter</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 11:43
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String authorization = exchange.getRequest().getHeaders()
                .getFirst("Authorization");

        if (StringUtils.isEmpty(authorization)) {
            return chain.filter(exchange);
        }

        String bearer = authorization.replace("Bearer", "");
        try {
            JWSObject parse = JWSObject.parse(bearer);
            String payload = parse.getPayload().toString();
            log.info("认证拦截器，user:  {}", payload);
            ServerHttpRequest request = exchange.getRequest()
                    .mutate()
                    .header("user", payload)
                    .build();
            exchange = exchange.mutate().request(request).build();

        } catch (ParseException e) {
            log.error("认证拦截器异常", e);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
