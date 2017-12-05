package com.controller;

import com.Application;
import com.domain.ReturnContent;
import com.domain.ReturnContentEnum;
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
    public ReturnContent setFilter(@RequestBody Map<String, Object> returnNumber){
        try {
            if (returnNumber.get("returnNumber") == null) {
                return new ReturnContent(ReturnContentEnum.PARAMETER_NOT_FOUND.getStatus(), ReturnContentEnum.PARAMETER_NOT_FOUND.getInfo());
            }
            int returnNum = (int) returnNumber.get("returnNumber");
            Application.userIntent.setReturnNumber(returnNum);
            return new ReturnContent(ReturnContentEnum.SUCCESS.getStatus(), ReturnContentEnum.SUCCESS.getInfo());
        }
        catch (ClassCastException e){
            return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(), ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
        }
    }
}
