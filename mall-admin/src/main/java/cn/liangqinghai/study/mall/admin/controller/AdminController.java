package cn.liangqinghai.study.mall.admin.controller;

import cn.liangqinghai.study.mall.admin.service.IAdminService;
import cn.liangqinghai.study.mall.common.api.ResultDTO;
import cn.liangqinghai.study.mall.common.domain.Oauth2TokenDTO;
import cn.liangqinghai.study.mall.common.domain.UserDTO;
import org.springframework.web.bind.annotation.*;
import cn.liangqinghai.study.mall.admin.service.feign.IAuthService;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.admin.controller</p>
 * <p>File name: AdminController</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/23 16:42
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final IAdminService adminService;
    private final IAuthService authService;

    public AdminController(IAdminService adminService,
                           IAuthService authService) {
        this.adminService = adminService;
        this.authService = authService;
    }

    @GetMapping("/load-by-username")
    public UserDTO loadByUsername(@RequestParam("username") String username) {

        return adminService.loadByUsername(username);

    }

    @PostMapping("/login")
    public ResultDTO<Oauth2TokenDTO> login(String username, String password) {

        Map<String, String> parameters = new HashMap<>(8);
        parameters.put("client_id", "admin-app");
        parameters.put("client_secret", "123456");
        parameters.put("grant_type", "password");
        parameters.put("username", username);
        parameters.put("password", password);
        return authService.getAccessToken(parameters);

    }

}
