package com.controller;

import com.Application;
import com.domain.Filter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Bruinx on 2017/11/30.
 */
@RestController
public class GenerateSQLController {
    @RequestMapping(value = "/generateSQL", method = RequestMethod.GET)
    public String generateSQL(){

        return "SQL statement";
    }

}

