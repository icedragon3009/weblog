package blog.ice.weblog.common.utils;

import blog.ice.weblog.common.enums.ResponseCodeEnum;
import blog.ice.weblog.common.exception.BizException;
import lombok.Data;

import java.io.Serializable;

/**
 * 响应参数工具栏
 * @param <T>
 */
@Data
public class Response<T> implements Serializable {
    //响应是否成功
    private boolean success;

    private String message;

    private String code;

    private T data;

    public static <T>  Response<T> success() {
        Response<T> response = new Response<>();
        response.setSuccess(true);
        return response;
    }

    public static <T> Response<T> success(T data) {
        Response<T> response =  new Response<T>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    // =================================== 失败响应 ===================================
    public static <T> Response<T> fail() {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        return response;
    }

    public static <T> Response<T> fail(String errorMessage) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setCode("10000");
        response.setMessage(errorMessage);
        return response;
    }

    public static <T> Response<T> fail(String errorCode, String errorMessage) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setCode(errorCode.isEmpty() ? errorCode: "10000");
        response.setMessage(errorMessage);
        return response;
    }

    public static <T> Response<T> fail(ResponseCodeEnum codeEnum) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setCode(codeEnum.getCode());
        response.setMessage(codeEnum.getMessage());
        return response;
    }

    public static <T> Response<T> fail(BizException e) {
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setCode(e.getCode());
        response.setMessage(e.getMessage());
        return response;
    }

}
