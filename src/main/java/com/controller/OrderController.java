package com.controller;

import com.Application;
import com.domain.Order;
import com.domain.ReturnContent;
import com.domain.ReturnContentEnum;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {
    @RequestMapping(value = "/setOrder", method = RequestMethod.POST)
    public ReturnContent setOrder(@RequestBody Order order){
        if (order.getOrder() == null || order.getObject() == null) {
            return new ReturnContent(ReturnContentEnum.PARAMETER_NOT_FOUND.getStatus(), ReturnContentEnum.PARAMETER_NOT_FOUND.getInfo());
        }
        if (!Application.userIntent.getOrders().contains(order)) {
            Application.userIntent.addOrder(order);
        } else {
            return new ReturnContent(ReturnContentEnum.ORDER_EXISTED.getStatus(), ReturnContentEnum.ORDER_EXISTED.getInfo());
        }
        return new ReturnContent(ReturnContentEnum.SUCCESS.getStatus(), ReturnContentEnum.SUCCESS.getInfo());
    }
}
