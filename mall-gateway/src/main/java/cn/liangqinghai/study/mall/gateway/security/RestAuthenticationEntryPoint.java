package cn.liangqinghai.study.mall.gateway.security;

import cn.hutool.json.JSONUtil;
import cn.liangqinghai.study.mall.common.api.ResultDTO;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.gateway.security</p>
 * <p>File name: RestAuthenticationEntryPoint</p>
 * <div>
 * <h3>Description: </h3>
 * 自定义返回结果
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 14:43
 */
@Component
public class RestAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.getHeaders().setAccessControlAllowOrigin("*");
        response.getHeaders().setCacheControl("no-cache");

        String jsonStr = JSONUtil.toJsonStr(ResultDTO.unauthorized(e.getMessage()));
        DataBuffer wrap = response.bufferFactory().wrap(jsonStr.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(wrap));

    }
}
