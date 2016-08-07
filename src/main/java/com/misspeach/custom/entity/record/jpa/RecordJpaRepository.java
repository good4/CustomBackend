package com.misspeach.custom.entity.record.jpa;

import com.misspeach.custom.entity.record.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by shizhan on 16/8/4.
 */
public interface RecordJpaRepository extends JpaRepository<Record,Long> {

//    @Query("select r from Record r where r.date LIKE ?1 and r.custom")
//    List<Record> findByDateAndCustomId(Date date, long custom_id);
}
