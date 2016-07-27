package com.misspeach.custom.web;

import com.misspeach.custom.entity.user.User;
import com.misspeach.custom.service.UserService;
//import com.mysql.jdbc.log.Log;
//import org.apache.commons.logging.Log;
//import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jiangtengfei on 16/7/20.
 */
@RestController
@RequestMapping("/")
public class UserController {
    //自动注入
    @Autowired
    UserService userService;

    //可以在用的时候用value使用,也可以在config类中事先配置
    @Value("${hello}")
    Long hello;

//    @RequestMapping(value = "/", method = RequestMethod.GET)
    //get方法用于从服务器上获取数据,将请求参数都要放在URL中 ?参数名
    //post方法用于上传数据
//    public Object user(String userName) {
//
//        //List<User> user =  userService.getUserCustoms(userName);
//        //System.out.println(user.toString());
//        //return user;
//    }
}
