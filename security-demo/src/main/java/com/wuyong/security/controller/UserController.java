package com.wuyong.security.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.collect.Lists;
import com.wuyong.security.dto.User;
import com.wuyong.security.exception.UserNotExistExcepton;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * created by JianGuo
 * on 2018/1/17
 * description:
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "获取所有用户信息")
    public List<User> getUserList(@RequestParam() String username, String size,
                                  @PageableDefault() Pageable pageable) {
        log.info("username:{}", username);
        log.info("size:{}", size);
        log.info("pageable:{}", pageable);
        List<User> userList = Lists.newArrayList();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        user1.setUsername("username1");
        user1.setPassword("password1");
        user2.setUsername("username2");
        user2.setPassword("password2");
        user3.setUsername("username3");
        user3.setPassword("password3");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        return userList;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    @ApiOperation(value = "根据id获取用户信息")
    public User getUserInfo(@ApiParam(value = "用户id") @PathVariable(name = "id") String id) {
//        throw new UserNotExistExcepton(id);
//        throw new RuntimeException(" run time exception: user not exist ");

//        log.info("id:{}", id);
        log.info("进入getUserInfo()服务");
        User user = new User();
        user.setUsername("tom");
        user.setPassword("tompass");
        return user;
    }

    @PostMapping
    @ApiOperation(value = "根据用户信息保存用户")
    public User saveUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors()
                    .stream()   // 流化
                    .forEach(
                            (result) -> {
                                FieldError fieldError = (FieldError) result;
                                log.info("字段：{}发生错误：{}", fieldError.getField(), fieldError.getDefaultMessage());
                            }
                    );
        }
        log.info("收到的user：{}", user);
        User user1 = new User();
        user1.setId("1");
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        return user1;
    }


}
