package com.misspeach.custom.service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.misspeach.custom.entity.custom.Custom;
import com.misspeach.custom.entity.custom.jpa.CustomJpaRepository;
import com.misspeach.custom.entity.user.jpa.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by shizhan on 16/8/10.
 */
@Service
@EnableScheduling
public class TimeTask {
    @Autowired
    CustomJpaRepository customJpaRepository;
    @Autowired
    UserJpaRepository userJpaRepository;
    @Autowired
    CustomService customService;

    protected static final Logger LOG = LoggerFactory.getLogger(TimeTask.class);

    // demo App defined in resources/jpush-api.conf

    public static final String TITLE = "Changing";

    public static JPushClient jpushClient = null;

    private Map<String,String> info=new HashMap<String,String>();

    private static final String appKey = "2eb4a8cb83384abdf6f2394f";
    private static final String masterSecret = "dc4db4848906113df9b2531e";
    private int isPushed=1;

    public static void SendPush() {
        // HttpProxy proxy = new HttpProxy("localhost", 3128);
        // Can use this https proxy: https://github.com/Exa-Networks/exaproxy
        ClientConfig clientConfig= ClientConfig.getInstance();
        jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);

    }
    public static PushPayload buildPushObject_android_alias_alert(String alart,String alias,Map<String,String> info) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())//设置接受的平台 android
                .setAudience(Audience.alias(alias))//
                .setNotification(Notification.android(alart, TITLE,info))
                .build();
    }

    @Scheduled(cron = "0/30 * * * * *")
    public void Push() {
        LOG.info("push a new notification..............");
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String shifen = sdf.format(now);
        if(isPushed==1){
            System.out.println(shifen);
            List<Custom> customList = userJpaRepository.findCustoms(shifen);
            LOG.info(String.valueOf(customList.size()));
            if (customList.size() > 0) {
                for (int i = 0; i < customList.size(); i++) {
                    Custom custom = customList.get(i);
                    LOG.info(custom.getCustom_name() + "," + custom.getAlarm_time());
                    LOG.info(String.valueOf(custom.getId()));
                    String user_name = userJpaRepository.findUser(custom.getId());
                    LOG.info(user_name);
                    String user_id=String.valueOf(userJpaRepository.findUserId(user_name));
                    LOG.info(user_id);
                    //将用户名和密码传送
                    info.put("user_name",user_name);
                    info.put("user_id",user_id);
                    //System.out.println(info.size());
                    //info.put("user_name",user_name);
                    // For push, all you need do is to build PushPayload object.
                    PushPayload payload = buildPushObject_android_alias_alert("~"+custom.getCustom_name()+"~时间到了哦!",user_name,info);
                    try {
                        PushResult result = jpushClient.sendPush(payload);
                        LOG.info("Got result - " + result);

                    } catch (APIConnectionException e) {
                        LOG.error("Connection error. Should retry later. ", e);

                    } catch (APIRequestException e) {
                        LOG.error("Error response from JPush server. Should review and fix it. ",e);
                        LOG.error("");
                        LOG.info("HTTP Status: " + e.getStatus());
                        LOG.info("Error Code: " + e.getErrorCode());
                        LOG.info("Error Message: " + e.getErrorMessage());
                        LOG.info("Msg ID: " + e.getMsgId());
                    }
                }
            }
            isPushed=0;
        }
        else if(isPushed==0)
            isPushed=1;
    }

    //每天的0点将所有习惯的已打卡字段置为0
    @Scheduled(cron = "0 0 0 * * ?")
    public void update() {
        LOG.info("update custom isRecorded ..............");
        List<Custom> customs = userJpaRepository.findAllCustoms();
        for (Custom custom : customs) {
            custom.setIsRecorded(0);
            customService.save(custom);
        }
    }
}
