package com.misspeach.custom.web;

import com.misspeach.custom.entity.custom.Custom;
import com.misspeach.custom.entity.user.User;
import com.misspeach.custom.service.CustomService;
import com.misspeach.custom.service.RecordService;
import com.misspeach.custom.service.UserService;
//import com.oracle.tools.packager.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

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
    @Autowired
    RecordService recordService;

    List<Custom> customList=null;
    User user=null;
    Custom custom=null;
    //String user_Id="";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUserCustoms(String userName){
//        System.out.println("userName="+userName);
        customList=customService.getUserCustomsByName(userName);
        //user=customService.getUserByUsername(userName);
        //user_Id=String.valueOf(user.getId());

        //System.out.println("connect!!!!");

        //<Custom> customList=customService.getUserCustoms(userName);
        String tmpStr="[";
        for(int i=0;i<customList.size();i++){
            Custom c=customList.get(i);
            String custom_Id=String.valueOf(c.getId());
            String custom_name=c.getCustom_name();
            String alarm_time=c.getAlarm_time();
            String insist_day=String.valueOf(c.getInsist_day());
            String category=c.getCategory();
            String target_day=String.valueOf(c.getTarget_day());
            String max_insist_day=String.valueOf(c.getMax_insist_day());
            String current_insist_day=String.valueOf(c.getCurrent_insist_day());
            String isRecorded=String.valueOf(c.getIsRecorded());
            System.out.println(custom_name+","+isRecorded);
            tmpStr+="{\"custom_Id\":\"" +custom_Id+"\",\"custom_name\":\""+custom_name+"\",\"alarm_time\":\""+alarm_time+"\",\"insist_day\":\""+insist_day+
                    "\",\"category\":\""+category+"\",\"target_day\":\""+target_day+
                    "\",\"max_insist_day\":\""+max_insist_day+ "\",\"current_insist_day\":\""+current_insist_day+
                    "\",\"isRecorded\":\""+isRecorded+"\"}";
            if(i!=customList.size()-1)
                tmpStr+=",";
//            System.out.println(tmpStr);
        }
        tmpStr+="]";
//        System.out.println(tmpStr);
        return tmpStr;
    }
    /*
    * 判断该习惯是否已经存在
    * */
    @RequestMapping(value = "/custom", method = RequestMethod.GET)
    public String isExist(String userName,String customName){
        String userNameDecode="";
        String customNameDecode="";
        try {
            userNameDecode= URLDecoder.decode(userName,"utf-8");
            customNameDecode=URLDecoder.decode(customName,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String re="{\"isExist\":\"custom not exist\"}";
        List<Custom> customList=customService.getUserCustomsByName(userNameDecode);
        for(Custom custom:customList){
            if(custom.getCustom_name().equals(customNameDecode))
            {
                re="{\"isExist\":\"custom exist\"}";
            }
        }
        return re;
    }
    /*
    * 插入一条新习惯
    * */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String  insertUserCustoms(@RequestParam(value = "user_id") String userId,
                                     @RequestParam(value = "custom_name") String customName,
                                     @RequestParam(value = "alarm_time") String alarmTime,
                                     @RequestParam(value = "target_day") String targetDay,
                                     @RequestParam(value = "category") String category){
        Custom custom=new Custom();
        custom.setAlarm_time(alarmTime);
        custom.setCategory(category);
        custom.setTarget_day(Integer.valueOf(targetDay));
        custom.setCustom_name(customName);

        customList=customService.getUserCustomsById(Long.valueOf(userId));
        customList.add(custom);
        user=customService.getUserByUserId(Long.valueOf(userId));
        user.setCustoms(customList);
        userService.save(user);
        Long custom_id=customService.getCustomId(customName);
        return String.valueOf(custom_id);
    }
    /*
    * 删除一条习惯
    * */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String deleteUserCustoms(@RequestParam(value = "custom_id") String customId){
        custom=customService.getCustomsByCustomId(Long.valueOf(customId));
        customService.delete(custom);
        //定义了级联,则该习惯ID对应的所有打卡记录也删除了
        return "delete success";
    }
    /*
    * 更新一条习惯
    * */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateUserCustom(@RequestParam(value = "custom_id") String customId,
                                   @RequestParam(value = "alarm_time") String alarmTime,
                                   @RequestParam(value = "target_day") String targetDay){
        custom=customService.getCustomsByCustomId(Long.valueOf(customId));
        custom.setAlarm_time(alarmTime);
        custom.setTarget_day(Integer.valueOf(targetDay));
        customService.save(custom);
        return "update success";
    }
}
