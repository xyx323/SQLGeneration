package com.domain;

/**
 * Created by Bruinx on 2017/12/4.
 */
public enum ReturnContentEnum {

    SUCCESS(0,"正常"),
    PARAMETER_TYPE_ERROR(1,"参数类型错误"),
    OBJECT_NOT_FOUND(2,"找不到对象"),
    QUERY_NOT_FOUND(2,"找不到子查询"),
    FILTER_NOT_FOUND(2,"找不到预过滤条件"),
    JOIN_NOT_FOUND(2,"找不到连接条件"),
    FILTER_EXISTED(3, "过滤条件已存在"),
    ORDER_EXISTED(3, "排序条件已存在"),
    PARAMETER_NOT_FOUND(4, "未找到对应参数"),
    OPERAND_OBJECT_TYPE_ERROR(5,"操作数对象类型与过滤条件对象不匹配"),
    LOAD_PROPERTIES_ERROR(6,"配置文件加载失败"),
    JOIN_CONDITION_ERROR(7,"连接解析失败"),
    PARSE_FILTER_ERROR(7,"过滤条件解析失败"),
    JOIN_DIRECTION_ERROR(8,"全连接方向错误");

    private ReturnContentEnum(Integer status, String info){
        this.status = status;
        this.info = info;
    }
    public Integer status;

    public String info;

    public Integer getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }


}
