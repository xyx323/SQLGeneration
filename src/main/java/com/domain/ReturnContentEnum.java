package com.domain;

/**
 * Created by Bruinx on 2017/12/4.
 */
public enum ReturnContentEnum {

    Return0(0,"正常"),
    Return1(1,"输入错误"),
    Return2(2,"找不到对象");

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
