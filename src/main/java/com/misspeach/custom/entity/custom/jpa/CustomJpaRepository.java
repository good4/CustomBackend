package com.misspeach.custom.entity.custom.jpa;

import com.misspeach.custom.entity.custom.Custom;
import com.misspeach.custom.entity.record.Record;
import com.misspeach.custom.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shizhan on 16/7/22.
 */

public interface CustomJpaRepository extends JpaRepository<Custom, Long> {

//    @Query(nativeQuery = true,value = "insert into custom (user_id,custom_name,target_day,category,alarm_time) values(?1,?2,?3,?4,?5)")
//    void insertCustom(Long userId,String custom_name,int target_day,String category,String alarm_time );

    @Query(nativeQuery = true,value = "select * from Record r where r.custom_id=?1 and date(r.date)=?2")
    List<Record> findByCustomIdAndDate(Long id,Date date);

    @Query("select c from Custom c where c.id=?1")
    Custom findByCustomId(Long id);

}

