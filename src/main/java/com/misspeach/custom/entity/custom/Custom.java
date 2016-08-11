package com.misspeach.custom.entity.custom;

import com.misspeach.custom.entity.record.Record;
import com.misspeach.custom.entity.user.User;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;
import java.util.List;

/**
 * Created by shizhan on 16/7/22.
 */
@Entity
@Table(name = "custom")
public class Custom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String custom_name;

    private String category;
    //目标天数
    private int target_day;
    //当前完成天数
    private int insist_day;
    //最大连续天数
    private int max_insist_day;
    //当前连续天数
    private int current_insist_day;

    private String alarm_time;

    //是否打卡
    private int isRecorded;


    //外键
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name="custom_id")
    private List<Record> records;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustom_name() {
        return custom_name;
    }

    public void setCustom_name(String custom_name) {
        this.custom_name = custom_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTarget_day() {
        return target_day;
    }

    public void setTarget_day(int target_day) {
        this.target_day = target_day;
    }

    public int getInsist_day() {
        return insist_day;
    }

    public void setInsist_day(int insist_day) {
        this.insist_day = insist_day;
    }

    public int getMax_insist_day() {
        return max_insist_day;
    }

    public void setMax_insist_day(int max_insist_day) {
        this.max_insist_day = max_insist_day;
    }

    public int getCurrent_insist_day() {
        return current_insist_day;
    }

    public void setCurrent_insist_day(int current_insist_day) {
        this.current_insist_day = current_insist_day;
    }

    public String getAlarm_time() {
        return alarm_time;
    }

    public void setAlarm_time(String alarm_time) {
        this.alarm_time = alarm_time;
    }

    public int getIsRecorded() {
        return isRecorded;
    }

    public void setIsRecorded(int isRecorded) {
        this.isRecorded = isRecorded;
    }
}
