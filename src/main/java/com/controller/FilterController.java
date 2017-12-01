package com.controller;

import com.Application;
import com.domain.Filter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FilterController {
    @RequestMapping(value = "/setFilter", method = RequestMethod.POST)
    public int setFilter(@RequestBody Filter filter){
        if (!Application.userIntent.getFilterList().contains(filter)){
            Application.userIntent.addFilter(filter);
        }
        else{
            return 2;
        }
        return 1;
    }

    @RequestMapping(value = "/setPredefinedFilter", method = RequestMethod.POST)
    public int setPredefinedFilter(@RequestBody Map<String, Object> predefinedFilter){
        if (predefinedFilter.get("filter") == null) {
            return 3;
        }
        int filterID = (int) predefinedFilter.get("filter");
        if (!Application.userIntent.getPredefinedFilterIds().contains(filterID)){
            Application.userIntent.addPredefinedFilter(filterID);
        }
        else{
            return 2;
        }
        return 1;
    }
}
