package com.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bruinx on 2017/11/30.
 */
public class UserIntent {

    private List<Integer> objects;
    //查询的对象Id列表
    private List<Filter> filters;
    //过滤条件列表
    private Filter filter;
    //应用于本意图的过滤条件
    private List<Integer> predefinedFilters;
    //预过滤条件列表
    private List<Integer> tables;
    //自定义的关联表
    private List<Integer> joinCondition;
    //连接条件
    private Filter joinFilter;
    //应用于本意图的join过滤条件
    private List<Order> orders;
    //排序规则列表
    private Boolean distinct;
    //是否删除重复记录
    private Integer returnNumber;
    //返回记录数目
    private List<Integer> logicOperators; // 1: and, 2: or


    public UserIntent() {
        objects = new ArrayList<>();
        filters = new ArrayList<>();
        predefinedFilters = new ArrayList<>();
        orders = new ArrayList<>();
        logicOperators = new ArrayList<>();
        joinCondition = new ArrayList<>();
        distinct = Boolean.FALSE;
        returnNumber = -1;
    }

    public Filter getJoinFilter() {
        return joinFilter;
    }

    public void setJoinFilter(Filter joinFilter) {
        this.joinFilter = joinFilter;
    }

    public List<Integer> getJoinCondition() {
        return joinCondition;
    }

    public void setJoinCondition(List<Integer> joinCondition) {
        this.joinCondition = joinCondition;
    }

    public List<Integer> getObjects() {
        return objects;
    }

    public void setObjects(List<Integer> objects) {
        this.objects = objects;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public List<Integer> getPredefinedFilters() {
        return predefinedFilters;
    }

    public void setPredefinedFilters(List<Integer> predefinedFilters) {
        this.predefinedFilters = predefinedFilters;
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

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public void addObjectID(int objectID){
        objects.add(objectID);
    }

    public void addFilter(Filter filter){
        filters.add(filter);
    }

    public void addPredefinedFilter(int predefinedFilterID){
        predefinedFilters.add(predefinedFilterID);
    }

    public List<Integer> getLogicOperators() {
        return logicOperators;
    }

    public void setLogicOperators(List<Integer> logicOperators) {
        this.logicOperators = logicOperators;
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

    public List<Integer> getTables() {
        return tables;
    }

    public void setTables(List<Integer> tables) {
        this.tables = tables;
    }
}
