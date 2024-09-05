package blog.ice.weblog.admin.config;

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
 * Knife4j配置类
 */
@Configuration
@EnableSwagger2WebMvc
@Profile("dev")
public class Knife4jAdminConfig {

    @Bean("adminApi")
    public Docket createApiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .groupName("Admin 后端文档")
                .select()
                .apis(RequestHandlerSelectors.basePackage("bloc.ice.weblog.admin.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("Weblog 后端接口文档")
                .description("Ice dragon 开发")
                .termsOfServiceUrl("https://github.com/IceDragon/Weblog")
                .contact(new Contact("Ice", "https://github.com/IceDragon/Weblog", "https://github.com/IceDragon"))
                .version("1.0")
                .build();
    }
}
