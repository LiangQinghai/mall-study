package cn.liangqinghai.study.mall.common.domain;

import lombok.Builder;
import lombok.Data;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.common.domain</p>
 * <p>File name: SwaggerProperties</p>
 * <div>
 * <h3>Description: </h3>
 * swagger通用配置
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 16:43
 */
@Data
@Builder
public class SwaggerProperties {

    /**
     * API文档生成基础路径
     */
    private String apiBasePackage;
    /**
     * 是否要启用登录认证
     */
    private boolean enableSecurity;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档描述
     */
    private String description;
    /**
     * 文档版本
     */
    private String version;
    /**
     * 文档联系人姓名
     */
    private String contactName;
    /**
     * 文档联系人网址
     */
    private String contactUrl;
    /**
     * 文档联系人邮箱
     */
    private String contactEmail;
}
