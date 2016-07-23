package com.misspeach.custom.entity.custom;

import com.misspeach.custom.entity.user.User;

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

    private String category;

    private int target;

    private int insist;

    private int max;

    private String alarmTime;
//    //外键
//    private User user;

    public Custom(String category, int target, int insist, int max, String alarmTime) {
        this.category = category;
        this.target = target;
        this.insist = insist;
        this.max = max;
        this.alarmTime = alarmTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getInsist() {
        return insist;
    }

    public void setInsist(int insist) {
        this.insist = insist;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
