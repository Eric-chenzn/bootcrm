package com.itchenzn.bootcrm.dao;

import com.itchenzn.bootcrm.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    //通过账号和密码查询用户
    public User findUser(@Param("usercode") String usercode, @Param("password") String password);
}
