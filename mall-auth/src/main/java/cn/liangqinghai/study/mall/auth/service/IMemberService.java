package cn.liangqinghai.study.mall.auth.service;

import cn.liangqinghai.study.mall.common.domain.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.auth.service</p>
 * <p>File name: IMemberService</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 17:18
 */
@FeignClient("mall-portal")
public interface IMemberService {

    /**
     * 根据用户名称获取用户
     *
     * @param username 用户名
     * @return 用户dto
     */
    @GetMapping("/sso/loadByUsername")
    UserDTO loadUserByUsername(@RequestParam String username);

}
