package com.misspeach.custom.entity.record;

import com.misspeach.custom.entity.custom.Custom;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by shizhan on 16/8/4.
 */
@Entity
@Table(name = "Record")
public class Record {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
