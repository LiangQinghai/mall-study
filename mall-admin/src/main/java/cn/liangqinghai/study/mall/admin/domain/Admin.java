package cn.liangqinghai.study.mall.admin.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField
    private String username;

    @TableField
    private String password;

    @TableField
    private String phone;

    /**
     * 头像
     */
    @TableField
    private String avatar;

    @TableField
    private String email;

    @TableField
    private String nickname;

    @TableField
    private String note;

    /**
     * 是否启用，0->禁用，1->启用
     */
    @TableField
    private Integer status;

    @TableField
    private LocalDateTime createTime;

    @TableField
    private LocalDateTime updateTime;

}
