package org.example.tliaswebmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.LoginInfo;
import org.example.tliaswebmanagement.pojo.Result;
import org.example.tliaswebmanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody Emp emp) {
        log.info("登录用户:{}",emp);

        LoginInfo loginInfo = loginService.loginByUsernameAndPassword(emp);
        return Result.success(loginInfo);
    }
}
