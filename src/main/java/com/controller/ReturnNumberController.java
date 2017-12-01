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
public class ReturnNumberController {
    @RequestMapping(value = "/setReturnNumber", method = RequestMethod.POST)
    public int setFilter(@RequestBody Map<String, Object> returnNumber){
        if (returnNumber.get("returnNumber") == null){
            return 3;
        }
        int returnNum = (int) returnNumber.get("returnNumber");
        Application.userIntent.setReturnNumber(returnNum);
        return 1;
    }
}
