package com.misspeach.custom.service;

import com.misspeach.custom.entity.custom.Custom;
import com.misspeach.custom.entity.user.User;
import com.misspeach.custom.entity.user.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangtengfei on 16/7/20.
 */
@Service

//UserService类对从数据库中取到的数据进行逻辑处理
//service可以像entity包一样分为若干个包管理
public class UserService {

    @Autowired
    UserJpaRepository userJpaRepository;
    public void save(User user){

        userJpaRepository.save(user);
    }
    public User findUserByUsername(String userName){

        return  userJpaRepository.findUserByUsername(userName);

    }
    public String getPassword(String username){
        String password = userJpaRepository.findPasswordByUsername(username);
        return   password;
    }

    public User getUserInfo(Long uid) {  //User在entity的user下定义,
        return userJpaRepository.getUserInfo(uid);  //getUserInfo在web层中的UserController中定义,名字随便起
    }
    public User getUserInfo(String name) {  //User在entity的user下定义,
        return userJpaRepository.findUserByUsername(name);  //getUserInfo在web层中的UserController中定义,名字随便起
    }

}
