package com.misspeach.custom.web;

import com.misspeach.custom.entity.custom.Custom;
import com.misspeach.custom.service.CustomService;
import com.oracle.tools.packager.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by shizhan on 16/7/26.
 */
@RestController
@RequestMapping("/")
public class CustomController {
    @Autowired
    CustomService customService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUserCustoms(String userName){
        //System.out.println("connect!!!!");
        List<Custom> customList=customService.getUserCustoms(userName);
        String []res=new String[customList.size()];
        String tmpStr="[";
        for(int i=0;i<customList.size();i++){
            Custom c=customList.get(i);
            String custom_name=c.getCustom_name();
            String alarm_time=c.getAlarm_time();
            String insist_day=String.valueOf(c.getInsist_day());

            res[i]=(customList.get(i).getCustom_name()+"|"+customList.get(i).getAlarm_time()+"|"+customList.get(i).getInsist_day()+"|");
            res[i]+=customService.getImageUrl(c.getCategory());

            String image_url=customService.getImageUrl(c.getCategory());
            tmpStr+="{\"custom_name\":\""+custom_name+"\",\"alarm_time\":\""+alarm_time+"\",\"insist_day\":\""+insist_day+"\",\"image_url\":\""+image_url+"\"}";
            if(i!=customList.size()-1)
                tmpStr+=",";
            else
                tmpStr+="]";
            //System.out.println(tmpStr);
            //tmpStr="";
        }
        return tmpStr;
    }
}
