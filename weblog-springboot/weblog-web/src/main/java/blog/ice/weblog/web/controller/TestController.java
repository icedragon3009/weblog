package blog.ice.weblog.web.controller;

import blog.ice.weblog.common.aspect.ApiOperationLog;
import blog.ice.weblog.common.enums.ResponseCodeEnum;
import blog.ice.weblog.common.exception.BizException;
import blog.ice.weblog.common.utils.JsonUtils;
import blog.ice.weblog.common.utils.Response;
import blog.ice.weblog.web.model.vo.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Api(tags = "测试模块")
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    @ApiOperation(value = "初次测试")
    public Response<LoginUser> test(@RequestBody @Validated LoginUser user) {
        System.out.println("进入测试接口");
//        if (bindingResult.hasErrors()) {
//            System.out.println("验证错误:" + JsonUtils.toJson(user));
//            String errMsg = bindingResult.getFieldErrors().
//                    stream().
//                    map(FieldError::getDefaultMessage).
//                    collect(Collectors.joining(", "));
//            throw new BizException(ResponseCodeEnum.PRODUCT_NOT_FOUND);
////            return Response.fail(errMsg);
//        }
        user.setCreateTime(LocalDateTime.now());
        user.setBirthday(LocalDate.of(1989, 8, 17));
        user.setTime(LocalTime.now());
        return Response.success(user);
    }
}
