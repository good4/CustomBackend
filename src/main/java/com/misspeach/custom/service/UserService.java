package com.misspeach.custom.service;

import com.misspeach.custom.entity.user.User;
import com.misspeach.custom.entity.user.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangtengfei on 16/7/20.
 */
@Service

//UserService类对从数据库中取到的数据进行逻辑处理
//service可以像entity包一样分为若干个包管理
public class UserService {

    @Autowired
    UserJpaRepository userJpaRepository;

    public User getOne(String userName) {
//        return userJpaRepository.findOne(userId);
        return userJpaRepository.findOneByUsername(userName);
    }
}
