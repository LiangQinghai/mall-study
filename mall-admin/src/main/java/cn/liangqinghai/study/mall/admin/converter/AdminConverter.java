package cn.liangqinghai.study.mall.admin.converter;

import cn.liangqinghai.study.mall.admin.domain.Admin;
import cn.liangqinghai.study.mall.common.domain.UserDTO;
import org.mapstruct.Mapper;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.admin.converter</p>
 * <p>File name: AdminConverter</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/23 16:36
 */
@Mapper(componentModel = "spring")
public interface AdminConverter {

    /**
     * admin转换到基本用户
     *
     * @param admin admin
     * @return user
     */
    UserDTO toUserDTO(Admin admin);

}
