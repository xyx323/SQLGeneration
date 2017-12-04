package com.controller;

import com.Application;
import com.domain.ReturnContentEnum;
import com.domain.ReturnContent;
import com.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ObjectController {
    private List<Integer> objectIDs;

//    private List<Integer> avaliableObjectIDs;

    @Autowired
    private ObjectRepository objectRepository;

    @RequestMapping(value = "/setObjects", method = RequestMethod.POST)
    public ReturnContent setObjects(@RequestBody Map<String, Object> params){
        if (params.get("objects") == null) {
            return new ReturnContent(ReturnContentEnum.Return1.getStatus(), ReturnContentEnum.Return1.getInfo());
        }
        Object obj = params.get("objects");
        if (obj instanceof List<?>){
            objectIDs = (List<Integer>) params.get("objects");
            for (int objectID : objectIDs){
                com.entity.Object objectEntity = objectRepository.findOne(objectID);
                if(objectEntity != null){
                    if (!Application.userIntent.getObjectsIDs().contains(objectID)){
                        Application.userIntent.addObjectID(objectID);
                    }
                }
                else{
                    return new ReturnContent(ReturnContentEnum.Return2.getStatus(), ReturnContentEnum.Return2.getInfo());
                }
            }
        }
        else{
            return new ReturnContent(ReturnContentEnum.Return1.getStatus(), ReturnContentEnum.Return1.getInfo());
        }
        return new ReturnContent(ReturnContentEnum.Return0.getStatus(), ReturnContentEnum.Return0.getInfo());
    }
}
