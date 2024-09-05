package blog.ice.weblog.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 业务异常
 */
@Getter
@Setter
public class BizException extends RuntimeException {

    private String code;

    private String message;

    public BizException(BaseExceptionInterface exception) {
        this.code = exception.getCode();
        this.message = exception.getMessage();
    }
}
