package com.misspeach.custom.web;

import com.misspeach.custom.entity.custom.Custom;
import com.misspeach.custom.entity.user.User;
import com.misspeach.custom.service.CustomService;
import com.misspeach.custom.service.UserService;
import com.oracle.tools.packager.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shizhan on 16/7/26.
 */
@RestController
@RequestMapping("/")
public class CustomController {
    @Autowired
    CustomService customService;
    @Autowired
    UserService userService;

    List<Custom> customList=null;
    User user=null;
    String user_Id="";
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUserCustoms(String userName){

        customList=customService.getUserCustoms(userName);
        user=customService.getUser(userName);
        user_Id=String.valueOf(user.getId());

        String tmpStr="[";
        for(int i=0;i<customList.size();i++){
            Custom c=customList.get(i);
            String custom_Id=String.valueOf(c.getId());
            String custom_name=c.getCustom_name();
            String alarm_time=c.getAlarm_time();
            String insist_day=String.valueOf(c.getInsist_day());
            String image_url=customService.getImageUrl(c.getCategory());
            String category=c.getCategory();
            String target_day=String.valueOf(c.getTarget_day());
            String max_insist_day=String.valueOf(c.getMax_insist_day());
            String current_insist_day=String.valueOf(c.getCurrent_insist_day());
            //System.out.println(image_url);
            tmpStr+="{\"custom_Id\":\"" +custom_Id+"\",\"custom_name\":\""+custom_name+"\",\"alarm_time\":\""+alarm_time+"\",\"insist_day\":\""+insist_day+
                    "\",\"image_url\":\""+image_url+"\",\"category\":\""+category+"\",\"target_day\":\""+target_day+
                    "\",\"max_insist_day\":\""+max_insist_day+ "\",\"current_insist_day\":\""+current_insist_day+
                    "\",\"user_Id\":\"" + user_Id + "\"}";
            if(i!=customList.size()-1)
                tmpStr+=",";
            else
                tmpStr+="]";
            System.out.println(tmpStr);
            //tmpStr="";
        }
        //System.out.println(tmpStr);
        return tmpStr;
    }

    @RequestMapping(value = "/custom", method = RequestMethod.GET)
    public String isExist(String userName,String customName){
//        Log.debug("in isExist");
        //System.out.println("in is exist");
        String re="{\"isExist\":\"custom not exist\"}";
        List<Custom> customList=customService.getUserCustoms(userName);
        for(Custom custom:customList){
            if(custom.getCustom_name().equals(customName))
            {
                re="{\"isExist\":\"custom exist\"}";
            }
        }
        return re;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String  insertUserCustoms(@RequestParam(value = "user_id") String userId,
                                     @RequestParam(value = "custom_name") String customName,
                                     @RequestParam(value = "alarm_time") String alarmTime,
                                     @RequestParam(value = "target_day") String targetDay,
                                     @RequestParam(value = "category") String category){
//            System.out.println(userId);
//            System.out.println(customName);
//        System.out.println(alarmTime);
//        System.out.println(targetDay);
//        System.out.println(category);
//        customService.insertCustom(Long.valueOf(userId),customName,alarmTime,targetDay,category);
        Custom custom=new Custom();
        custom.setAlarm_time(alarmTime);
        custom.setCategory(category);
        custom.setTarget_day(Integer.valueOf(targetDay));
        custom.setCustom_name(customName);
        customList.add(custom);

        //user.setId(Long.valueOf(userId));
        user.setCustoms(customList);
        userService.save(user);

        return "success";
    }
}
