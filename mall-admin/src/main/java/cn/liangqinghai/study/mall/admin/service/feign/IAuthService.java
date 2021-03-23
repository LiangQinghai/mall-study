package cn.liangqinghai.study.mall.admin.service.feign;

import cn.liangqinghai.study.mall.common.api.ResultDTO;
import cn.liangqinghai.study.mall.common.domain.Oauth2TokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.auth.service.feign</p>
 * <p>File name: AuthService</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/23 18:00
 */
@FeignClient("mall-auth")
public interface IAuthService {

    @PostMapping("/oauth/token")
    ResultDTO<Oauth2TokenDTO> getAccessToken(@RequestParam Map<String, String> parameters);

}
