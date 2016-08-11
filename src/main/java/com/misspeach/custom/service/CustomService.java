package com.misspeach.custom.service;

import com.misspeach.custom.entity.category.jpa.CategoryJpaRepository;
import com.misspeach.custom.entity.custom.Custom;
import com.misspeach.custom.entity.custom.jpa.CustomJpaRepository;
import com.misspeach.custom.entity.user.User;
import com.misspeach.custom.entity.user.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shizhan on 16/7/26.
 */
@Service
public class CustomService {
    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    CategoryJpaRepository categoryJpaRepository;
    @Autowired
    CustomJpaRepository customJpaRepository;
/*
* 根据用户名查询该用户的习惯
* */
    public List<Custom> getUserCustomsByName(String userName) {
        List<Custom> customs=userJpaRepository.findByUsername(userName);
        return customs;
    }
    /*
    * 根据用户ID查用户习惯
    * */
    public List<Custom> getUserCustomsById(Long userId) {
        List<Custom> customs=userJpaRepository.findByUserId(userId);
        return customs;
    }
    /*
    * 根据用户名查对应用户
    * */
    public User getUserByUsername(String userName){
        return userJpaRepository.findUserByUsername(userName);
    }
    /*
    * 根据用户ID查对应用户
    * */
    public User getUserByUserId(Long userId){
        return userJpaRepository.findUserByUserId(userId);
    }
    /*
    * 根据习惯ID查对应习惯
    * */
    public Custom getCustomsByCustomId(Long customId){
        return customJpaRepository.findByCustomId(customId);
    }
    /*
    * 删除一条习惯
    * */
    public void delete(Custom custom){
        customJpaRepository.delete(custom);
    }
    /*
    * 保存对习惯的修改
    * */
    public void save(Custom custom){
        customJpaRepository.save(custom);
    }

}
