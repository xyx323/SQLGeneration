package com.controller;

import com.Application;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Bruinx on 2017/11/30.
 */
@RestController
public class DistinctRecordsController {

    @RequestMapping(value = "/setDistinctRecords", method = RequestMethod.POST)
    public int setFilter(@RequestBody Map<String, Object> distinctRecord){
        if (distinctRecord.get("distinct") == null) {
            return 3;
        }
        int distinct = (int) distinctRecord.get("distinct");
        Application.userIntent.setDistinct(distinct);
        return 1;
    }
}
