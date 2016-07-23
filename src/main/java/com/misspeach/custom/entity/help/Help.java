package com.misspeach.custom.entity.help;

import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;

/**
 * Created by shizhan on 16/7/23.
 */
@Entity
@Table (name="help")
public class Help {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String resolution;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
}
