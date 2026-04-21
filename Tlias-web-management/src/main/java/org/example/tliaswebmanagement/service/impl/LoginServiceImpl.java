package org.example.tliaswebmanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.tliaswebmanagement.exception.custom.UsernameOrPasswordError;
import org.example.tliaswebmanagement.mapper.EmpMapper;
import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.LoginInfo;
import org.example.tliaswebmanagement.service.LoginService;
import org.example.tliaswebmanagement.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public LoginInfo loginByUsernameAndPassword(Emp emp) {
        LoginInfo loginInfo = empMapper.loginByUsernameAndPassword(emp);
        if (loginInfo!=null){
            log.info("登录成功:{}",loginInfo);

            Map<String,Object> map=new HashMap<>();
            map.put("id",loginInfo.getId());
            map.put("username",loginInfo.getUsername());
            String token = JwtUtils.generateJwt(map);

            loginInfo.setToken(token);
            return loginInfo;
        }else{
            throw new UsernameOrPasswordError("用户名或密码错误");
        }
    }
}
