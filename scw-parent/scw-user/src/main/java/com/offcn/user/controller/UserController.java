package com.offcn.user.controller;

import com.offcn.user.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 胡长生
 * @create 2020-10-22 17:25
 */
@Api("swagger测试")
@RestController
public class UserController {

    @ApiOperation("测试方法hello")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name="name",value = "姓名",required = true),
            @ApiImplicitParam(name="email",value = "电子邮件")
    })
    @GetMapping("/hello")
    public String hello(String name) {
        return "ok!" + name;
    }

    @ApiOperation("保存用户的方法")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name="name",value = "姓名",required = true),
            @ApiImplicitParam(name="email",value = "电子邮件")
    })
    @PostMapping("/user")
    public User save(String name,String eamil) {
        User user = new User();
        user.setName(name);
        user.setEmail(eamil);
        return user;
    }
}
