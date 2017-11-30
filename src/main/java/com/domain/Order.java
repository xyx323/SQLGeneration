package com.domain;

/**
 * Created by Bruinx on 2017/11/30.
 */
public class Order {

    private Integer ObjectId;
    //排序对象Id
    private Integer order;
    //排序方法


    public Integer getObjectId() {
        return ObjectId;
    }

    public void setObjectId(Integer objectId) {
        ObjectId = objectId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
