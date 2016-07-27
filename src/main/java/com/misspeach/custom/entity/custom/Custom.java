package com.misspeach.custom.entity.custom;

import com.misspeach.custom.entity.user.User;

import javax.jws.soap.SOAPBinding;
import javax.persistence.*;

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

    private int target_day;

    private int insist_day;

    private int max_day;

    private String alarm_time;

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

    public int getMax_day() {
        return max_day;
    }

    public void setMax_day(int max_day) {
        this.max_day = max_day;
    }

    public String getAlarm_time() {
        return alarm_time;
    }

    public void setAlarm_time(String alarm_time) {
        this.alarm_time = alarm_time;
    }
}
