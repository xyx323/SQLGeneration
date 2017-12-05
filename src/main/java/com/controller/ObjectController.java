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
            return new ReturnContent(ReturnContentEnum.PARAMETER_NOT_FOUND.getStatus(), ReturnContentEnum.PARAMETER_NOT_FOUND.getInfo());
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
                    return new ReturnContent(ReturnContentEnum.OBJECT_NOT_FOUND.getStatus(), ReturnContentEnum.OBJECT_NOT_FOUND.getInfo());
                }
            }
        }
        else{
            return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(), ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
        }
        return new ReturnContent(ReturnContentEnum.SUCCESS.getStatus(), ReturnContentEnum.SUCCESS.getInfo());
    }
}
