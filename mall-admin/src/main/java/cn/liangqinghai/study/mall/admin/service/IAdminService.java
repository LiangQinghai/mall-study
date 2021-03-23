package cn.liangqinghai.study.mall.admin.service;

import cn.liangqinghai.study.mall.admin.domain.Admin;
import cn.liangqinghai.study.mall.common.domain.UserDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.admin.service</p>
 * <p>File name: IAdminService</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/23 16:29
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 通过username查找
     *
     * @param username 用户名称
     * @return admin
     */
    UserDTO loadByUsername(String username);

}
