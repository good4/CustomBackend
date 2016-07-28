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
/*
* 根据用户名查询该用户的习惯
* */
    public List<Custom> getUserCustoms(String userName) {
//        User user=userJpaRepository.findByUsername(userName);
        List<Custom> customs=userJpaRepository.findByUsername(userName);
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
}
