package cn.liangqinghai.study.mall.auth.service;

import cn.liangqinghai.study.mall.common.domain.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.auth.service</p>
 * <p>File name: AdminService</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 17:16
 */
@FeignClient("mall-admin")
public interface IAdminService {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名称
     * @return userDto
     */
    @GetMapping("/admin/load-by-username")
    UserDTO loadUserByUsername(@RequestParam String username);

}
