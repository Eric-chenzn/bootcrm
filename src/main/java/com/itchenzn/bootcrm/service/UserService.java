package com.itchenzn.bootcrm.service;

import com.itchenzn.bootcrm.pojo.User;

public interface UserService {
    //通过账号和密码查询用户
    public User findUser(String usercode, String password);

}
