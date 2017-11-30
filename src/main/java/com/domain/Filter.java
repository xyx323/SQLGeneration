package com.domain;

/**
 * Created by Bruinx on 2017/11/30.
 */
public class Filter {

    private Integer ObjectId;
    //过滤条件涉及的对象ID
    private Integer Operator;
    //过滤条件操作符
    private Integer OperandType;
    //过滤条件操作数类型
    private String Operand;
    //过滤条件操作数（数值，字符串，子查询等等）

    public Integer getObjectId() {
        return ObjectId;
    }

    public void setObjectId(Integer objectId) {
        ObjectId = objectId;
    }

    public Integer getOperator() {
        return Operator;
    }

    public void setOperator(Integer operator) {
        Operator = operator;
    }

    public Integer getOperandType() {
        return OperandType;
    }

    public void setOperandType(Integer operandType) {
        OperandType = operandType;
    }

    public String getOperand() {
        return Operand;
    }

    public void setOperand(String operand) {
        Operand = operand;
    }
}
