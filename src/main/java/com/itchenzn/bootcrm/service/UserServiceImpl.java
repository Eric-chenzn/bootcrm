package com.itchenzn.bootcrm.service;

import com.itchenzn.bootcrm.dao.UserDao;
import com.itchenzn.bootcrm.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    //通过账号和密码查询用户
    @Override
    public User findUser(String usercode, String password) {
        User user=userDao.findUser(usercode,password);
        return user;
    }
}
