package com.misspeach.custom.web;

import com.misspeach.custom.entity.custom.Custom;
import com.misspeach.custom.entity.user.User;
import com.misspeach.custom.service.UserService;
//import com.mysql.jdbc.log.Log;
//import org.apache.commons.logging.Log;
//import org.apache.juli.logging.Log;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    User user=null;
    //可以在用的时候用value使用,也可以在config类中事先配置
    @Value("${hello}")
    Long hello;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String username, String password) {
        User user=userService.findUserByUsername(username);

        if(user!=null){
            if(password.equals(user.getPassword())){
                return "{\"message\":\"success\",\"id\":\""+user.getId()+"\"}";
            }else{
                return "{\"message\":\"fail\"}";
            }

        }else{
            return "{\"message\":\"fail\"}";
        }
    }


    //先不用连接数据库 你看看怎么做能直接返回{"message":"success","user_id":1,"user_name":"test"} 在浏览器上 访问链接 大约可能是http:\\localhost:8080\register?username=test&password=123 返回{"message":"success","user_id":1,"user_name":"test"}
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(String username,String password){
        User u=userService.findUserByUsername(username);
        if(u==null) {
            User user = new User();
            user.setUser_name(username);
            user.setPassword(password);
            userService.save(user);
            return "{\"message\":\"success\"}";
        }else{
            return "{\"message\":\"fail\"}";
        }
    }
    @RequestMapping(value = "/forgetp", method = RequestMethod.GET)
    public String forgetp(String username){
        String pwd=userService.getPassword(username);
        if(pwd==null){
            return "{\"message\":\"fail\"}";
        }else{
            return "{\"message\":\"success\",\"password\":\""+pwd+"\"}";
        }
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET) //getUserInfo自己定义,在stdio中发Http请求时用
    public String getUserInfo(@RequestParam(value = "user_name") String user_name){  //Long是字段id的类型,uid自己起的名字,在stdio中parameterinfo="?uid=" + 1; 时用
//public String getUserInfo(Long uid){  //Long是字段id的类型,uid自己起的名字,在stdio中parameterinfo="?uid=" + 1; 时用
        //System.out.println("connect!!!!");
        User user=userService.getUserInfo(user_name);

        System.out.println(user);
        String tmpStr ; //定义tmpStr接收Json解析后的数据

        tmpStr ="{\"id\":\""+user.getId()+"\",\"user_name\":\""+user.getUser_name()+"\"," +
                "\"sex\":\"" + user.getSex() + "\",\"brithday\":\"" + user.getBrithday() + "\"," +
                "\"signature\":\"" + user.getSignature() +"\"," +
               /* */"\"password\":\"" + user.getPassword() + "\"," +
                /* */"\"image\":\"" + user.getImage()  +

                "\"}";
//Json格式解析方法: "id":""+user.getId()  id是数据库中定义的字段名,user.getId()是user的get的方法
        //必须和数据库字段名保持一致,在entity中的user中的User.java中定义
        return tmpStr;
    }
    //    @RequestMapping(value = "/", method = RequestMethod.GET)
    //get方法用于从服务器上获取数据,将请求参数都要放在URL中 ?参数名
    //post方法用于上传数据
//    public Object user(String userName) {
//
//        //List<User> user =  userService.getUserCustoms(userName);
//        //System.out.println(user.toString());
//        //return user;
//    }
    @RequestMapping(value = "/changeUserInfo", method = RequestMethod.POST)
    public String changeUserCustoms(@RequestParam(value = "uid") String userId,
                                    @RequestParam(value = "user_name") String user_name,
                                    @RequestParam(value = "password") String password,
                                    @RequestParam(value = "email") String email,
                                    @RequestParam(value = "tel") String tel,
                                    @RequestParam(value = "sex") String sex,
                                    @RequestParam(value = "birthday") String birthday,
                                    @RequestParam(value = "signature") String signature,
                                    @RequestParam(value = "image") String image ){
        user=userService.getUserInfo(Long.valueOf(userId)); //找到这条记录
        //设置新的值
        user.setUser_name(user_name);
        user.setPassword(password);
        user.setEmail(email);
        user.setTel(tel);
        user.setSex(Integer.valueOf(sex));
        user.setBrithday(birthday);
        user.setSignature(signature);


        user.setImage(image);

        userService.save(user); //保存
        return "update success";
    }





}//code: (int)错误码(成功1失败0)
       // message: (string)成功返回 : \\\"success\\\"  失败返回 : \\\"fail\\\"
        //data: 返回数据



//get方法用于从服务器上获取数据,将请求参数都要放在URL中 ?参数名
    //post方法用于上传数据
//    public Object user(String userName) {
//
//        //List<User> user =  userService.getUserCustoms(userName);
//        //System.out.println(user.toString());
//        //return user;
//    }

