package com.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruinx on 2017/11/30.
 */
public class UserIntent {

    private List<Integer> objectsIDs;
    //查询的对象Id列表
    private List<Filter> filterList;
    //过滤条件列表
    private List<Integer> predefinedFilterIds;
    //预过滤条件列表
    private List<Order> orders;
    //排序规则列表
    private Boolean distinct;
    //是否删除重复记录
    private Integer returnNumber;
    //返回记录数目


    public UserIntent() {
        objectsIDs = new ArrayList<>();
        filterList = new ArrayList<>();
        predefinedFilterIds = new ArrayList<>();
        orders = new ArrayList<>();
        distinct = Boolean.FALSE;
        returnNumber = -1;
    }

    public List<Integer> getObjectsIDs() {
        return objectsIDs;
    }

    public void setObjectsIDs(List<Integer> objectsIDs) {
        this.objectsIDs = objectsIDs;
    }

    public List<Filter> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<Filter> filterList) {
        this.filterList = filterList;
    }

    public List<Integer> getPredefinedFilterIds() {
        return predefinedFilterIds;
    }

    public void setPredefinedFilterIds(List<Integer> predefinedFilterIds) {
        this.predefinedFilterIds = predefinedFilterIds;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    public Integer getReturnNumber() {
        return returnNumber;
    }

    public void setReturnNumber(Integer returnNumber) {
        this.returnNumber = returnNumber;
    }

    public void addObjectID(int objectID){
        objectsIDs.add(objectID);
    }

    public void addFilter(Filter filter){
        filterList.add(filter);
    }

    public void addPredefinedFilter(int predefinedFilterID){
        predefinedFilterIds.add(predefinedFilterID);
    }

    public void addOrder(Order order){
        for (int i = 0; i < orders.size(); i++){
            Order o = orders.get(i);
            if (o.getObject().equals(order.getObject())){
                orders.set(i, order);
                return;
            }
        }
        orders.add(order);
    }
}
