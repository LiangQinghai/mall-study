package cn.liangqinghai.study.mall.gateway.security;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import cn.liangqinghai.study.mall.common.domain.UserDTO;
import cn.liangqinghai.study.mall.gateway.config.IgnoreUrlsConfig;
import com.nimbusds.jose.JWSObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.gateway.security</p>
 * <p>File name: AuthorizationManager</p>
 * <div>
 * <h3>Description: </h3>
 * 鉴权管理器
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 11:54
 */
@Slf4j
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext context) {

        ServerHttpRequest request = context.getExchange().getRequest();
        URI uri = request.getURI();

        AntPathMatcher matcher = new AntPathMatcher();
        List<String> urls = ignoreUrlsConfig.getUrls();
        // 白名单过
        for (String url : urls) {
            if (matcher.match(url, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }

        // 跨域过
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }

        String authorization = request.getHeaders().getFirst("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            return Mono.just(new AuthorizationDecision(false));
        }
        String bearer = authorization.replace("Bearer", "");
        JWSObject parse;
        try {
            parse = JWSObject.parse(bearer);
        } catch (ParseException e) {
            log.error("解析jwt信息失败：", e);
            return Mono.just(new AuthorizationDecision(false));
        }
        String payload = parse.getPayload().toString();
        UserDTO userDTO = JSONUtil.toBean(payload, UserDTO.class);
        if ("admin-app".equals(userDTO.getClientId()) && !matcher.match("/mall-admin/**", uri.getPath())) {
            return Mono.just(new AuthorizationDecision(false));
        }
        if ("portal-app".equals(userDTO.getClientId()) && matcher.match("/mall-admin/**", uri.getPath())) {
            return Mono.just(new AuthorizationDecision(false));
        }

        // 非管理段路径过
        if (!matcher.match("/mall-admin/**", uri.getPath())) {
            return Mono.just(new AuthorizationDecision(true));
        }

        //管理端路径鉴权
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("auth:resourceRolesMap");
        Iterator<Object> iterator = entries.keySet().iterator();
        List<String> authorized = new ArrayList<>();
        while (iterator.hasNext()) {
            String next = (String) iterator.next();
            if (matcher.match(next, uri.getPath())) {
                authorized.addAll(Convert.toList(String.class, entries.get(next)));
            }
        }

        authorized = authorized.stream()
                .map(i -> i = "ROLE_" + i)
                .collect(Collectors.toList());
        return authentication
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorized::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
