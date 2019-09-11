package com.wx.wxsecuritydemo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.wx.wxsecuritydemo.dto.User;
import com.wx.wxsecuritydemo.dto.UserQueryCondition;
import com.wx.wxsecuritydemo.exception.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wangxiang
 * Date: 2019/9/9
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 根据ID获取用户的详细信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}")
    @JsonView({User.UserDetailView.class})
    public User getInfo(@PathVariable String id) {
//        User user = new User();
//        user.setUsername("tom");
//        return user;


//        异常测试
        throw new UserNotExistException("123");
    }

    /**
     * 根据条件分页查询用户信息
     *
     * @param condition
     * @param pageable
     * @return
     */
    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    public List<User> query(UserQueryCondition condition,
                            @PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable) {

        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    /**
     * 创建一个用户，校验了密码不能为空，为空的时候记录, @Validated注解与BindingResult配合使用
     *
     * @param user
     * @param error
     * @return
     */
    @PostMapping
    public User createUser(@RequestBody @Validated User user, BindingResult error) {
        if (error.hasErrors()) {
            error.getAllErrors().stream().forEach(v -> System.out.println(v.getDefaultMessage()));
        }
        User re = new User();
        re.setUsername("tom");
        re.setId("1");
        return re;
    }

    /**
     * 更新用户信息
     *
     * @param id
     * @param user
     * @param error
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public User updateUser(@PathVariable String id, @Validated @RequestBody User user, BindingResult error) {
        if (error.hasErrors()) {
            error.getAllErrors().stream().forEach(v -> {
                FieldError fieldError = (FieldError) v;
                System.out.println(fieldError.getField() + ":" + fieldError.getDefaultMessage());
            });
        }
        User re = new User();
        re.setUsername("阿汤哥");
        re.setPassword("壮志凌云");
        re.setId(id);
        return user;
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }
}
