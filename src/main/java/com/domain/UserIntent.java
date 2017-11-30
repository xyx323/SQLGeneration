package com.domain;

import java.util.List;

/**
 * Created by Bruinx on 2017/11/30.
 */
public class UserIntent {

    private List<Integer> ObjectsIDs;
    //查询的对象Id列表
    private List<Filter> FilterList;
    //过滤条件列表
    private List<Integer> PredefinedFilterIds;
    //预过滤条件列表
    private Integer Distinct;
    //是否删除重复记录
    private Integer ReturnNumber;
    //返回记录数目


    public List<Integer> getObjectsIDs() {
        return ObjectsIDs;
    }

    public void setObjectsIDs(List<Integer> objectsIDs) {
        ObjectsIDs = objectsIDs;
    }

    public List<Filter> getFilterList() {
        return FilterList;
    }

    public void setFilterList(List<Filter> filterList) {
        FilterList = filterList;
    }

    public List<Integer> getPredefinedFilterIds() {
        return PredefinedFilterIds;
    }

    public void setPredefinedFilterIds(List<Integer> predefinedFilterIds) {
        PredefinedFilterIds = predefinedFilterIds;
    }

    public Integer getDistinct() {
        return Distinct;
    }

    public void setDistinct(Integer distinct) {
        Distinct = distinct;
    }

    public Integer getReturnNumber() {
        return ReturnNumber;
    }

    public void setReturnNumber(Integer returnNumber) {
        ReturnNumber = returnNumber;
    }
}
