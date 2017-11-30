package com.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ObjectController {
    private List<Integer> objectIDs;

    @RequestMapping(value = "/setObjects", method = RequestMethod.POST)
    public int setObjects(@RequestBody Map<String, Object> oIDs){

        return 1;
    }
}
