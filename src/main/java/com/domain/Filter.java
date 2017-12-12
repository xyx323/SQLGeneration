package com.domain;

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

        if (object != null ? !object.equals(filter.object) : filter.object != null) return false;
        if (operator != null ? !operator.equals(filter.operator) : filter.operator != null) return false;
        if (operandType != null ? !operandType.equals(filter.operandType) : filter.operandType != null) return false;
        return operand != null ? operand.equals(filter.operand) : filter.operand == null;
    }

    @Override
    public int hashCode() {
        int result = object != null ? object.hashCode() : 0;
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (operandType != null ? operandType.hashCode() : 0);
        result = 31 * result + (operand != null ? operand.hashCode() : 0);
        return result;
    }

    public boolean isAllFieldFilled() {
        return object != null && operator != null && operandType != null && operand != null;
    }
}
