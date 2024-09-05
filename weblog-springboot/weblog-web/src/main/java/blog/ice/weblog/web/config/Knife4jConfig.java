package blog.ice.weblog.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Knife4j配置
 */
//Configuration注解指定此类为配置类
@Configuration
//EnableSwagger2WebMvc注解：启动Swagger2
@EnableSwagger2WebMvc
//Profile注解：表示只有特定环境类才会被创建
@Profile("dev")
public class Knife4jConfig {

    @Bean("webApi")
    public Docket createApiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .groupName("Ice_dragon 前端文档")
                .select()
                .apis(RequestHandlerSelectors.basePackage("blog.ice.weblog.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("Weblog前后端接口文档")
                .description("Springboot + vue 3.2 + vite 4.3 开发")
                .termsOfServiceUrl("http://localhost:8080")
                .contact(new Contact("ice dragon", "http://localhost:8080", "icedragon.lee817@gmail.com"))
                .version("1.0")
                .build();
    }
}
