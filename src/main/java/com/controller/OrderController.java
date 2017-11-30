package com.controller;

import com.Application;
import com.domain.Order;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {
    @RequestMapping(value = "/setOrder", method = RequestMethod.POST)
    public int setOrder(@RequestBody Order order){
        if (!Application.userIntent.getOrders().contains(order)){
            Application.userIntent.addOrder(order);
        }
        else {
            return 2;
        }
        return 1;
    }
}
