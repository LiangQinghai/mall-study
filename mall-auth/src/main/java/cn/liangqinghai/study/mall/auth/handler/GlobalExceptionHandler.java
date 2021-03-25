package cn.liangqinghai.study.mall.auth.handler;

import cn.liangqinghai.study.mall.common.api.IResult;
import cn.liangqinghai.study.mall.common.api.ResultDTO;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.auth.handler</p>
 * <p>File name: GlobalExceptionHandler</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/24 15:11
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = OAuth2Exception.class)
    public ResultDTO<?> exception(OAuth2Exception oAuth2Exception) {

        return ResultDTO.failed(new IResult() {
            @Override
            public Integer code() {
                return oAuth2Exception.getHttpErrorCode();
            }

            @Override
            public String message() {
                return oAuth2Exception.getMessage();
            }
        });

    }

}
