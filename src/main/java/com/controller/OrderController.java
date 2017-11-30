package com.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {
    @RequestMapping(value = "/setOrder", method = RequestMethod.POST)
    public int setOrder(@RequestBody Map<String, Object> order){
        return 1;
    }
}
