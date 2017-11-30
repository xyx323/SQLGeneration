package com.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Bruinx on 2017/11/30.
 */
public class ReturnNumberController {
    @RequestMapping(value = "/setDistinctRecords", method = RequestMethod.POST)
    public int setFilter(@RequestBody Integer returnNumber){

        return 1;
    }
}
