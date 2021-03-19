package cn.liangqinghai.study.mall.gateway.filter;

import cn.liangqinghai.study.mall.gateway.config.IgnoreUrlsConfig;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.gateway.filter</p>
 * <p>File name: WhiteListFilter</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 11:36
 */
@Component
public class WhiteListFilter implements WebFilter {

    private final IgnoreUrlsConfig ignoreUrlsConfig;

    public WhiteListFilter(IgnoreUrlsConfig ignoreUrlsConfig) {
        this.ignoreUrlsConfig = ignoreUrlsConfig;
    }

    @Override
    public @NonNull
    Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();

        AntPathMatcher matcher = new AntPathMatcher();
        List<String> urls = ignoreUrlsConfig.getUrls();
        for (String url : urls) {
            if (matcher.match(url, uri.getPath())) {
                request = request.mutate().header("Authorization", "").build();
                exchange = exchange.mutate().request(request).build();
                return chain.filter(exchange);
            }
        }

        return chain.filter(exchange);

    }
}
