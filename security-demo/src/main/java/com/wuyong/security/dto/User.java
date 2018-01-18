package com.wuyong.security.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.wuyong.security.validator.MyConstraint;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * created by JianGuo
 * on 2018/1/17
 * description:
 */
@Data
public class User {

    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{};

    @JsonView(UserSimpleView.class)
    private String id;
    @JsonView(UserSimpleView.class)
    @MyConstraint
    @ApiModelProperty(value = "用户名")
    private String username;
    @JsonView(UserDetailView.class)
    @NotBlank(message = "密码不能为空")
    private String password;
}
