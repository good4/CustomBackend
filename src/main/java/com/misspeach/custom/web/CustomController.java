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
        List<Custom> customList=customService.getUserCustoms(userName);
        //Log.debug(String.valueOf(customList.size()));
        System.out.println(String.valueOf(customList.size()));
        String []res=new String[customList.size()];
        for(int i=0;i<customList.size();i++){
            res[i]=(customList.get(i).getCustom_name()+"|"+customList.get(i).getAlarm_time()+"|"+customList.get(i).getMax_day()+"|");
            res[i]+=customService.getImageUrl(customList.get(i).getCategory());
//            Log.debug(userName+"的第"+i+"个习惯是: "+res[i]);
            System.out.println(userName+"的第"+i+"个习惯是: "+res[i]);
        }
//        String customImage="";
        return "first";
    }
}
