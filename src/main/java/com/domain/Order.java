package com.domain;

/**
 * Created by Bruinx on 2017/11/30.
 */
public class Order {

    private Integer object;
    //排序对象Id
    private Integer order;
    //排序方法


    public Integer getObject() {
        return object;
    }

    public void setObject(Integer object) {
        this.object = object;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order1 = (Order) o;

        if (object != null ? !object.equals(order1.object) : order1.object != null) return false;
        return order != null ? order.equals(order1.order) : order1.order == null;
    }

    @Override
    public int hashCode() {
        int result = object != null ? object.hashCode() : 0;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }
}
