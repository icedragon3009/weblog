package blog.ice.weblog.common.aspect;

import java.lang.annotation.*;

/*
aspectj 注解说明
在配置 AOP 切面之前，我们需要了解下 aspectj 相关注解的作用：

@Aspect：声明该类为一个切面类；
@Pointcut：定义一个切点，后面跟随一个表达式，表达式可以定义为切某个注解，也可以切某个 package 下的方法；
切点定义好后，就是围绕这个切点做文章了：

@Before: 在切点之前，织入相关代码；
@After: 在切点之后，织入相关代码;
@AfterReturning: 在切点返回内容后，织入相关代码，一般用于对返回值做些加工处理的场景；
@AfterThrowing: 用来处理当织入的代码抛出异常后的逻辑处理;
@Around: 环绕，可以在切入点前后织入代码，并且可以自由的控制何时执行切点；

 */

//这个元注解用于指定注解的保留策略，即注解在何时生效。
// RetentionPolicy.RUNTIME 表示该注解将在运行时保留，这意味着它可以通过反射在运行时被访问和解析。
@Retention(RetentionPolicy.RUNTIME)
// 这个元注解用于指定注解的目标元素，即可以在哪些地方使用这个注解。
// ElementType.METHOD 表示该注解只能用于方法上。这意味着您只能在方法上使用这个特定的注解。
@Target({ElementType.METHOD})
//这个元注解用于指定被注解的元素是否会出现在生成的Java文档中。
// 如果一个注解使用了 @Documented，那么在生成文档时，被注解的元素及其注解信息会被包含在文档中。
// 这可以帮助文档生成工具（如 JavaDoc）在生成文档时展示关于注解的信息。
@Documented
public @interface ApiOperationLog {

    /**
     * API 功能描述
     *
     */
    String description() default "";
}
