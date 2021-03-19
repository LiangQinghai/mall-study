package cn.liangqinghai.study.mall.auth.service.impl;

import cn.liangqinghai.study.mall.auth.domain.SecurityUser;
import cn.liangqinghai.study.mall.auth.service.IAdminService;
import cn.liangqinghai.study.mall.auth.service.IMemberService;
import cn.liangqinghai.study.mall.common.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.auth.service.impl</p>
 * <p>File name: UserDetailsServiceImpl</p>
 * <div>
 * <h3>Description: </h3>
 *
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 17:14
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IAdminService adminService;
    private final IMemberService memberService;

    public UserDetailsServiceImpl(IAdminService adminService,
                                  IMemberService memberService) {
        this.adminService = adminService;
        this.memberService = memberService;
    }

    @Resource
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String clientId = request.getParameter("client_id");
        UserDTO userDTO;

        if ("admin-app".equals(clientId)) {
            userDTO = adminService.loadUserByUsername(username);
        } else {
            userDTO = memberService.loadUserByUsername(username);
        }

        if (userDTO == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        userDTO.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(userDTO);

        if (!securityUser.isEnabled()) {
            throw new DisabledException("账户被禁用");
        }

        if (!securityUser.isAccountNonLocked()) {
            throw new LockedException("账户被锁");
        }

        if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException("账号已过期");
        }

        if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("凭证过期");
        }

        return securityUser;
    }
}
