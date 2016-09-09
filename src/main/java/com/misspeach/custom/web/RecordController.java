package com.misspeach.custom.web;

import com.misspeach.custom.entity.custom.Custom;
import com.misspeach.custom.entity.record.Record;
import com.misspeach.custom.service.CustomService;
import com.misspeach.custom.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by candy on 2016/8/5.
 */
@RestController
@RequestMapping("/")

public class RecordController {
    @Autowired
    RecordService recordService;
    @Autowired
    CustomService customService;

    List<Record> recordList = null;
    Custom custom = null;

    @RequestMapping(value = "/record", method = RequestMethod.GET)
    public String getRecords(Long CustomId) {
        System.out.println("come in");
        List<Record> recordList = recordService.getRecords(CustomId);
        String tmpStr = "[";
        for (int i = 0; i < recordList.size(); i++) {
            Record r = recordList.get(i);
            Date date = r.getDate();
            //Date->String
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date_str = sdf.format(date);
            tmpStr += "{\"date_str\":\"" + date_str + "\"}";
            if (i != recordList.size() - 1)
                tmpStr += ",";
            else
                tmpStr += "]";
        }

        return tmpStr;
//        return null;
    }


    @RequestMapping(value = "/clockin", method = RequestMethod.POST)
    public String clockIn(@RequestParam(value = "custom_id") String customId,
                          @RequestParam(value = "date") String dateStr,
                          @RequestParam(value = "insist_day") String insistDay,
                          @RequestParam(value = "max_insist_day") String maxInsistDay,
                          @RequestParam(value = "current_insist_day") String currentInsistDay,
                          @RequestParam(value = "is_record")String isRecorded) throws ParseException {
        System.out.println(customId + dateStr + insistDay + maxInsistDay + currentInsistDay + isRecorded);
        //添加record
        Record record = new Record();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟

        java.util.Date date = sdf.parse(dateStr);
        record.setDate(date);

        recordList = recordService.getRecords(Long.valueOf(customId));
        recordList.add(record);

        //更新custom
        custom = customService.getCustomsByCustomId(Long.valueOf(customId));
        custom.setRecords(recordList);
        custom.setInsist_day(Integer.valueOf(insistDay));
        custom.setMax_insist_day(Integer.valueOf(maxInsistDay));
        custom.setCurrent_insist_day(Integer.valueOf(currentInsistDay));
        custom.setIsRecorded(Integer.valueOf(isRecorded));

        customService.save(custom);

        return "clock in success";
    }
}

