package blog.ice.weblog.web.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class User {

    private int id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    private String password;

    private Integer sex;

}
