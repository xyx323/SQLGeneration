package com.domain;

import java.util.Objects;

/**
 * Created by Bruinx on 2017/11/30.
 */
public class Filter {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Filter filter = (Filter) o;
        return Objects.equals(object, filter.object) &&
                Objects.equals(operator, filter.operator) &&
                Objects.equals(operandType, filter.operandType) &&
                Objects.equals(operand, filter.operand);
    }

    @Override
    public int hashCode() {

        return Objects.hash(object, operator, operandType, operand);
    }

    public boolean isAllFieldFilled() {
        return object != null && operator != null && operandType != null && operand != null;
    }
}
