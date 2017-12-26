package com.domain;

import java.util.List;

/**
 * Created by Bruinx on 2017/11/30.
 */
public class Filter {
    private Integer filterType; // 1: parent, 2: child Filter, 3: child predefined filter

    private Integer predefinedFilterID;

    private List<Filter> children;

    private List<Integer> logicOperators; // 1: and, 2: or

    private Integer object;
    //过滤条件涉及的对象ID
    private Integer operator;
    //过滤条件操作符
    private Integer operandType;
    //过滤条件操作数类型
    private Object operand;
    //过滤条件操作数（数值，字符串，子查询等等）

    public Integer getObject() {
        return object;
    }

    public void setObject(Integer object) {
        this.object = object;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Integer getOperandType() {
        return operandType;
    }

    public void setOperandType(Integer operandType) {
        this.operandType = operandType;
    }

    public Object getOperand() {
        return operand;
    }

    public void setOperand(Object operand) {
        this.operand = operand;
    }

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }

    public List<Filter> getChildren() {
        return children;
    }

    public void setChildren(List<Filter> children) {
        this.children = children;
    }

    public List<Integer> getLogicOperators() {
        return logicOperators;
    }

    public void setLogicOperators(List<Integer> logicOperators) {
        this.logicOperators = logicOperators;
    }

    public Integer getPredefinedFilterID() {

        return predefinedFilterID;
    }

    public void setPredefinedFilterID(Integer predefinedFilterID) {
        this.predefinedFilterID = predefinedFilterID;
    }

    public boolean isAllFieldFilled() {
        return object != null && operator != null && operandType != null && operand != null;
    }
}
