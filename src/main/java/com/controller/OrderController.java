package com.controller;

import com.Application;
import com.domain.Order;
import com.domain.ReturnContent;
import com.domain.ReturnContentEnum;
import com.entity.Object;
import com.repository.universe.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private ObjectRepository objectRepository;

    @RequestMapping(value = "/setOrder", method = RequestMethod.POST)
    public ReturnContent setOrder(@RequestBody Order order){
        if (order.getOrder() == null || order.getObject() == null) {
            return new ReturnContent(ReturnContentEnum.PARAMETER_NOT_FOUND.getStatus(), ReturnContentEnum.PARAMETER_NOT_FOUND.getInfo());
        }
        Object o = objectRepository.findOne(order.getObject());
        if(o != null){
            if (!Application.userIntent.getOrders().contains(order)) {
                Application.userIntent.addOrder(order);
            } else {
                return new ReturnContent(ReturnContentEnum.ORDER_EXISTED.getStatus(), ReturnContentEnum.ORDER_EXISTED.getInfo());
            }
        }else{
            return new ReturnContent(ReturnContentEnum.OBJECT_NOT_FOUND.getStatus(), ReturnContentEnum.OBJECT_NOT_FOUND.getInfo());
        }


        return new ReturnContent(ReturnContentEnum.SUCCESS.getStatus(), ReturnContentEnum.SUCCESS.getInfo());
    }
}
