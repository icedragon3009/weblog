package blog.ice.weblog.common.exception;

import blog.ice.weblog.common.enums.ResponseCodeEnum;
import blog.ice.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 全局异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    /**
     * 处理业务异常
     * @param request HttpRequest
     * @param e bizException
     * @return Response
     */
    //表示处理哪种异常
    @ExceptionHandler(value = BizException.class)
    //返回http请求
    @ResponseBody
    public Response<Object> handleBizException(HttpServletRequest request, BizException e) {
        log.warn("{} request fail, code: {}, msg: {}", request.getRequestURI(), e.getCode(), e.getMessage());
        return Response.fail(e);
    }

    /**
     * 处理全局异常
     * @param request 请求
     * @param e 错误
     * @return rsp
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response<Object> handleException(HttpServletRequest request, Exception e) {
        log.error("{} request error, ", request.getRequestURI(), e);
        return Response.fail(ResponseCodeEnum.SYSTEM_ERROR);
    }

    /**
     * 处理参数异常
     * @param request 请求
     * @param e 错误
     * @return rsp
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Response<Object> handleException(HttpServletRequest request, MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuffer sb = new StringBuffer();

        Optional.of(bindingResult.getFieldErrors()).ifPresent(errors -> {
            errors.forEach(error ->
                    sb.append(error.getField())
                            .append(" ")
                            .append(error.getDefaultMessage())
                            .append(", 当前值: '")
                            .append(error.getRejectedValue())
                            .append("'; ")

            );
        });

        String errorMsg = sb.toString();
        String errorCode = ResponseCodeEnum.PARAMS_NOT_VALID.getCode();
        log.error("{} request params error, code: {}, msg: {}", request.getRequestURI(), errorCode, errorMsg);
        return Response.fail(errorCode, errorMsg);
    }
}
