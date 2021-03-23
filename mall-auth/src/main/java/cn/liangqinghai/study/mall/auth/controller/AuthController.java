package cn.liangqinghai.study.mall.auth.controller;

import cn.liangqinghai.study.mall.common.api.ResultDTO;
import cn.liangqinghai.study.mall.common.domain.Oauth2TokenDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.Map;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.auth.controller</p>
 * <p>File name: AuthController</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 17:41
 */
@RestController
@Api(tags = "认证中心接口")
@RequestMapping("/oauth")
public class AuthController {

    private final TokenEndpoint tokenEndpoint;

    public AuthController(TokenEndpoint tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    @SneakyThrows
    @ApiOperation("Oauth2获取token")
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResultDTO<Oauth2TokenDTO> postAccessToken(@ApiIgnore Principal principal,
                                                     @ApiIgnore @RequestParam Map<String, String> parameters) {

        OAuth2AccessToken token = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        assert token != null;
        Oauth2TokenDTO bearer = Oauth2TokenDTO.builder()
                .token(token.getValue())
                .refreshToken(token.getRefreshToken().getValue())
                .expiresIn(token.getExpiresIn())
                .tokenHead("Bearer").build();

        return ResultDTO.success(bearer);

    }

}
