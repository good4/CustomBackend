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
//        User user=userJpaRepository.findByUsername(userName);
        List<Custom> customs=userJpaRepository.findByUsername(userName);
        //System.out.println("in customService :"+user.getSignature());
        //System.out.println(user.getCustoms().size());
        //List<Custom> userCustoms= user.getCustoms();
        //System.out.println(String.valueOf(customs.size()));
        return customs;
    }
    public List<Custom> getUserCustomsById(Long userId) {
//        User user=userJpaRepository.findByUsername(userName);
        List<Custom> customs=userJpaRepository.findByUserId(userId);
        //System.out.println("in customService :"+user.getSignature());
        //System.out.println(user.getCustoms().size());
        //List<Custom> userCustoms= user.getCustoms();
        //System.out.println(String.valueOf(customs.size()));
        return customs;
    }
    public String getImageUrl(String category){

        String imageUrl=categoryJpaRepository.findImageBycategory(category);
        return imageUrl;
    }
    public User getUserByUsername(String userName){
        return userJpaRepository.findUserByUsername(userName);
    }
    public User getUserByUserId(Long userId){
        return userJpaRepository.findUserByUserId(userId);
    }
    /*
    * 根据用户ID,习惯名,目标坚持时间,分类和提醒时间创建一条新的习惯
    * */
    public void insertCustom(Long userId,String customName,int targetDay,String category,String alarm_time){
        customJpaRepository.insertCustom(userId,customName,targetDay,category,alarm_time);
        //customJpaRepository.save(userId,customName,targetDay,category,alarm_time);
    }
    public void save(Custom custom){
        customJpaRepository.save(custom);
    }
}
