package org.example.tliaswebmanagement.service;

import org.example.tliaswebmanagement.pojo.Emp;
import org.example.tliaswebmanagement.pojo.LoginInfo;

public interface LoginService {

    LoginInfo loginByUsernameAndPassword(Emp emp);
}
