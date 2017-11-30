package com.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FilterController {
    @RequestMapping(value = "/setFilter", method = RequestMethod.POST)
    public int setFilter(@RequestBody Map<String, Object> filter){

        return 1;
    }

    @RequestMapping(value = "/setPredefinedFilter", method = RequestMethod.POST)
    public int setPredefinedFilter(@RequestBody Map<String, Object> predefinedFilter){

        return 1;
    }
}
