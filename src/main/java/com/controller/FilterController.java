package com.controller;

import com.Application;
import com.domain.Filter;
import com.domain.ReturnContent;
import com.domain.ReturnContentEnum;
import com.repository.FilterRepository;
import com.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FilterController {
    @Autowired
    private FilterRepository filterRepository;

    @Autowired
    private ObjectRepository objectRepository;

    @RequestMapping(value = "/setFilter", method = RequestMethod.POST)
    public ReturnContent setFilter(@RequestBody Filter filter){
        Object objectEntity = objectRepository.findOne(filter.getObject());
        if (objectEntity == null) {
            return new ReturnContent(ReturnContentEnum.OBJECT_NOT_FOUND.getStatus(), ReturnContentEnum.OBJECT_NOT_FOUND.getInfo());
        }
        if (!Application.userIntent.getFilterList().contains(filter)) {
            Application.userIntent.addFilter(filter);
        } else {
            return new ReturnContent(ReturnContentEnum.FILTER_EXISTED.getStatus(), ReturnContentEnum.FILTER_EXISTED.getInfo());
        }
        return new ReturnContent(ReturnContentEnum.SUCCESS.getStatus(), ReturnContentEnum.SUCCESS.getInfo());
    }

    @RequestMapping(value = "/setPredefinedFilter", method = RequestMethod.POST)
    public ReturnContent setPredefinedFilter(@RequestBody Map<String, Object> predefinedFilter){
        try {
            if (predefinedFilter.get("filter") == null) {
                return new ReturnContent(ReturnContentEnum.PARAMETER_NOT_FOUND.getStatus(), ReturnContentEnum.PARAMETER_NOT_FOUND.getInfo());
            }
            int filterID = (int) predefinedFilter.get("filter");

            if (filterRepository.findOne(filterID) == null){
                return new ReturnContent(ReturnContentEnum.FILTER_NOT_FOUND.getStatus(), ReturnContentEnum.FILTER_NOT_FOUND.getInfo());
            }
            if (!Application.userIntent.getPredefinedFilterIds().contains(filterID)) {
                Application.userIntent.addPredefinedFilter(filterID);
            } else {
                return new ReturnContent(ReturnContentEnum.FILTER_EXISTED.getStatus(), ReturnContentEnum.FILTER_EXISTED.getInfo());
            }
            return new ReturnContent(ReturnContentEnum.SUCCESS.getStatus(), ReturnContentEnum.SUCCESS.getInfo());
        }
        catch (ClassCastException e){
            return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(), ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
        }
    }
}
