package cn.liangqinghai.study.mall.admin.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.admin.domain</p>
 * <p>File name: Admin</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/23 15:44
 */
@Data
@TableName("t_admin")
public class Admin implements Serializable {

    private Long id;

    private String username;

    private String password;

    private String phone;

    /**
     * 头像
     */
    private String avatar;

    private String email;

    private String nickname;

    private String note;

    /**
     * 是否启用，0->禁用，1->启用
     */
    private Integer status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
