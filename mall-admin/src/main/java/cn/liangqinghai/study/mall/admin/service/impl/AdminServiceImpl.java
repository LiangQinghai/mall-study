package cn.liangqinghai.study.mall.admin.service.impl;

import cn.liangqinghai.study.mall.admin.converter.AdminConverter;
import cn.liangqinghai.study.mall.admin.domain.Admin;
import cn.liangqinghai.study.mall.admin.mapper.AdminMapper;
import cn.liangqinghai.study.mall.admin.service.IAdminService;
import cn.liangqinghai.study.mall.common.domain.UserDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.admin.service.impl</p>
 * <p>File name: AdminServiceImpl</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/23 16:30
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    private final AdminConverter adminConverter;

    public AdminServiceImpl(AdminConverter adminConverter) {
        this.adminConverter = adminConverter;
    }

    @Override
    public UserDTO loadByUsername(String username) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Admin admin = getOne(queryWrapper);
        return adminConverter.toUserDTO(admin);
    }
}
