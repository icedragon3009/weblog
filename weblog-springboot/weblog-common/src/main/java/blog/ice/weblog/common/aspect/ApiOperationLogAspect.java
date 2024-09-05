package blog.ice.weblog.common.aspect;

import blog.ice.weblog.common.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class ApiOperationLogAspect {
    /** 以自定义 @ApiOperationLog 注解为切点，凡是添加 @ApiOperationLog 的方法，都会执行环绕中的代码 */
    @Pointcut("@annotation(blog.ice.weblog.common.aspect.ApiOperationLog)")
    public void apiOperationLog(){}

    @Around("apiOperationLog()")
    public Object apiOperationLog(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            long startTime = System.currentTimeMillis();

            MDC.put("trackId", UUID.randomUUID().toString());
            //获取被请求的类和方法
//            String className = joinPoint.getSignature().getDeclaringTypeName();
            String classname = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();

            //请求入参
            Object[] args = joinPoint.getArgs();
            //入参转json
            String argsJson = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(", "));

            String description = getApiOperationLogDescription(joinPoint);
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            log.info("------请求结束[{}],耗时：{}ms,请求类：{}，请求方法：{}，入参：{}， 出参：{}",
                    description, endTime - startTime, classname, methodName,argsJson, JsonUtils.toJson(result));
            return result;
        } finally {
            MDC.clear();
        }
    }

    private String getApiOperationLogDescription(ProceedingJoinPoint joinPoint) {
        // 1. 从 ProceedingJoinPoint 获取 MethodSignature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 2. 使用 MethodSignature 获取当前被注解的 Method
        Method method = methodSignature.getMethod();
        // 3. 从 Method 中提取 LogExecution 注解
        ApiOperationLog annotation = method.getAnnotation(ApiOperationLog.class);
        // 4. 从 LogExecution 注解中获取 description 属性
        return annotation.description();
    }

    private Function<Object, String> toJsonStr() {
//        return args -> JsonUtils.toJson(args);
        return JsonUtils::toJson;
    }
}
