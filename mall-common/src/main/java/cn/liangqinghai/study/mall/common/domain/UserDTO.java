package cn.liangqinghai.study.mall.common.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.common.domain</p>
 * <p>File name: UserDTO</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 14:22
 */
@Data
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String password;

    private Integer status;

    private String clientId;

    private List<String> roles;

}
