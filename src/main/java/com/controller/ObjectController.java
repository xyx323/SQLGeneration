package com.controller;

import com.Application;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ObjectController {
    private List<Integer> objectIDs;

    @RequestMapping(value = "/setObjects", method = RequestMethod.POST)
    public int setObjects(@RequestBody Map<String, Object> params){
        if (params.get("objects") == null) {
            return 3;
        }
        Object obj = params.get("objects");
        if (obj instanceof List<?>){
            objectIDs = (List<Integer>) params.get("objects");
            for (int objectID : objectIDs){
                if (!Application.userIntent.getObjectsIDs().contains(objectID)){
                    Application.userIntent.addObjectID(objectID);
                }
            }
        }
        else{
            return 2;
        }
        return 1;
    }
}
