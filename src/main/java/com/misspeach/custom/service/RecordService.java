package com.misspeach.custom.service;

import com.misspeach.custom.entity.custom.jpa.CustomJpaRepository;
import com.misspeach.custom.entity.record.Record;
import com.misspeach.custom.entity.record.jpa.RecordJpaRepository;
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
    @Autowired
    RecordJpaRepository recordJpaRepository;
    //查询打卡记录
    public List<Record> getRecords (Long CustomId)
    {
        List<Record> records = customJpaRepository.findRecordsByCustomId(CustomId);
        return records;
        //            return null;
    }
    public int findRecord(Long custom_id,Date date){
        //System.out.println(custom_id);
        List<Record> records=customJpaRepository.findByCustomIdAndDate(custom_id,date);
        System.out.println(custom_id+","+records.size());
        return records.size();
    }
}
