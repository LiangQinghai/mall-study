package cn.liangqinghai.study.mall.common.api;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>Project name: mall-study</p>
 * <p>Package name: cn.liangqinghai.study.mall.common.api</p>
 * <p>File name: ResultDTO</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/19 14:47
 */
@Data
public class ResultDTO<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    protected ResultDTO() {

    }

    protected ResultDTO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO<>(BaseResultEnum.OK.code(), BaseResultEnum.OK.message(), data);
    }

    public static <T> ResultDTO<T> failed(IResult result, T data) {
        return new ResultDTO<>(result.code(), result.message(), data);
    }

    public static <T> ResultDTO<T> failed(IResult result) {
        return failed(result, null);
    }

    public static <T> ResultDTO<T> unauthorized(T data) {
        return failed(BaseResultEnum.UNAUTHORIZED, data);
    }

    public static <T> ResultDTO<T> unauthorized() {
        return failed(BaseResultEnum.UNAUTHORIZED);
    }

    public static <T> ResultDTO<T> forbidden(T data) {
        return failed(BaseResultEnum.FORBIDDEN, data);
    }

    public static <T> ResultDTO<T> forbidden() {
        return failed(BaseResultEnum.FORBIDDEN);
    }

}
