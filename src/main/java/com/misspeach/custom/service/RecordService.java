package com.misspeach.custom.service;

import com.misspeach.custom.entity.custom.jpa.CustomJpaRepository;
import com.misspeach.custom.entity.record.Record;
<<<<<<< HEAD
import com.misspeach.custom.entity.record.jpa.RecordJpaRepository;
=======
>>>>>>> f4f6a63a556752bebf35e68a708ae7f473ac5845
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by shizhan on 16/8/5.
 */
@Service
public class RecordService {
    @Autowired
    CustomJpaRepository customJpaRepository;
<<<<<<< HEAD
    @Autowired
    RecordJpaRepository recordJpaRepository;
    //查询打卡记录
    public List<Record> getRecords (Long CustomId)
    {
        List<Record> records = customJpaRepository.findRecordsByCustomId(CustomId);
        return records;
        //            return null;
    }
=======
>>>>>>> f4f6a63a556752bebf35e68a708ae7f473ac5845
    public int findRecord(Long custom_id,Date date){
        //System.out.println(custom_id);
        List<Record> records=customJpaRepository.findByCustomIdAndDate(custom_id,date);
        System.out.println(custom_id+","+records.size());
        return records.size();
    }
}
