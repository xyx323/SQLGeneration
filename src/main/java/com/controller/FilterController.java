package com.controller;

import com.Application;
import com.domain.Filter;
import com.domain.ReturnContent;
import com.domain.ReturnContentEnum;
import com.entity.DataField;
import com.entity.Object;
import com.entity.QueryStatement;
import com.repository.DataFieldRepository;
import com.repository.FilterRepository;
import com.repository.ObjectRepository;
import com.repository.QueryStatementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class FilterController {
    @Autowired
    private FilterRepository filterRepository;

    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private DataFieldRepository dataFieldRepository;

    @Autowired
    private QueryStatementRepository queryStatementRepository;

    private List<String> dbNumberTypes = new ArrayList(Arrays.asList("TINYINT",  "SMALLINT", "MEDIUMINT", "INT", "INTEGER",
            "BIGINT", "FLOAT", "BIGINT", "FLOAT", "DOUBLE", "DECIMAL"));

    private List<String> dbTextTypes = new ArrayList(Arrays.asList("CHAR", "VARCHAR", "TINYTEXT", "TEXT", "MEDIUMTEXT",
            "LONGTEXT"));

    @RequestMapping(value = "/setFilter", method = RequestMethod.POST)
    public ReturnContent setFilter(@RequestBody Filter filter){
        if (!filter.isAllFieldFilled()){
            return new ReturnContent(ReturnContentEnum.PARAMETER_NOT_FOUND.getStatus(), ReturnContentEnum.PARAMETER_NOT_FOUND.getInfo());
        }
        Object objectEntity = objectRepository.findOne(filter.getObject());
        if (objectEntity == null) {
            return new ReturnContent(ReturnContentEnum.OBJECT_NOT_FOUND.getStatus(), ReturnContentEnum.OBJECT_NOT_FOUND.getInfo());
        }
        if (objectEntity.getObject_type() == 1 && filter.getOperandType() == 1){
            String fieldType = findObjectFieldType(objectEntity);
            if (fieldType != null){
                if (filter.getOperand() instanceof List<?>){
                    List<java.lang.Object> operands = (List<java.lang.Object>) filter.getOperand();
                    for (java.lang.Object o : operands){
                        if (o instanceof Integer || o instanceof Float || o instanceof Double){
                            if (!(dbNumberTypes.contains(fieldType.toUpperCase()))){
                                return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                                        ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
                            }
                        } else if (o instanceof String){
                            if (!(dbTextTypes.contains(fieldType.toUpperCase()))){
                                return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                                        ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
                            }
                        }
                    }
                } else if (filter.getOperand() instanceof Integer || filter.getOperand() instanceof Float || filter.getOperand() instanceof Double){
                    if (!(dbNumberTypes.contains(fieldType.toUpperCase()))){
                        return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                                ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
                    }
                } else if (filter.getOperand() instanceof String){
                    if (!(dbTextTypes.contains(fieldType.toUpperCase()))){
                        return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                                ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
                    }
                }
            }
        } else if (objectEntity.getObject_type() == 1 && filter.getOperandType() == 2){
            String fieldType = findObjectFieldType(objectEntity);
            if (fieldType != null) {
                if (filter.getOperand() instanceof List<?>) {
                    List<java.lang.Object> operands = (List<java.lang.Object>) filter.getOperand();
                    for (java.lang.Object o : operands) {
                        if (o instanceof Integer) {
                            Object operandField = objectRepository.findOne((int) o);
                            if (operandField == null){
                                return new ReturnContent(ReturnContentEnum.OBJECT_NOT_FOUND.getStatus(),
                                        ReturnContentEnum.OBJECT_NOT_FOUND.getInfo());
                            }
                            String operandFieldType = findObjectFieldType(operandField);
                            if (operandFieldType != null) {
                                if (!isTypeMatch(fieldType, operandFieldType)){
                                    return new ReturnContent(ReturnContentEnum.OPERAND_OBJECT_TYPE_ERROR.getStatus(),
                                            ReturnContentEnum.OPERAND_OBJECT_TYPE_ERROR.getInfo());
                                }
                            }
                        } else {
                            return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                                    ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
                        }
                    }
                } else if (filter.getOperand() instanceof Integer) {
                    Object operandField = objectRepository.findOne((int) filter.getOperand());
                    if (operandField == null){
                        return new ReturnContent(ReturnContentEnum.OBJECT_NOT_FOUND.getStatus(),
                                ReturnContentEnum.OBJECT_NOT_FOUND.getInfo());
                    }
                    String operandFieldType = findObjectFieldType(operandField);
                    if (operandFieldType != null) {
                        if (!isTypeMatch(fieldType, operandFieldType)){
                            return new ReturnContent(ReturnContentEnum.OPERAND_OBJECT_TYPE_ERROR.getStatus(),
                                    ReturnContentEnum.OPERAND_OBJECT_TYPE_ERROR.getInfo());
                        }
                    }
                } else {
                    return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                            ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
                }
            }
        } else if (filter.getOperandType() == 3 || filter.getOperandType() == 4){
            // 当操作数的类型为子查询时判断操作数是否为整数，即子查询的id
            if (filter.getOperand() instanceof List<?>) {
                List<java.lang.Object> operands = (List<java.lang.Object>) filter.getOperand();
                for (java.lang.Object o : operands) {
                    if (!(o instanceof Integer)) {
                        return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                                ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
                    }
                    QueryStatement qs = queryStatementRepository.findOne((int) o);
                    if (qs == null){
                        return new ReturnContent(ReturnContentEnum.QUERY_NOT_FOUND.getStatus(),
                                ReturnContentEnum.QUERY_NOT_FOUND.getInfo());
                    }
                }
            } else if (filter.getOperand() instanceof Integer) {
                QueryStatement qs = queryStatementRepository.findOne((int) filter.getOperand());
                if (qs == null) {
                    return new ReturnContent(ReturnContentEnum.QUERY_NOT_FOUND.getStatus(),
                            ReturnContentEnum.QUERY_NOT_FOUND.getInfo());
                }
            } else {
                return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                        ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
            }

        }
        if (!Application.userIntent.getAllFilters().contains(filter)) {
            Application.userIntent.addFilter(filter);
        } else {
            return new ReturnContent(ReturnContentEnum.FILTER_EXISTED.getStatus(), ReturnContentEnum.FILTER_EXISTED.getInfo());
        }
        return new ReturnContent(ReturnContentEnum.SUCCESS.getStatus(), ReturnContentEnum.SUCCESS.getInfo());
    }

    @RequestMapping(value = "/setPredefinedFilter", method = RequestMethod.POST)
    public ReturnContent setPredefinedFilter(@RequestBody Map<String, java.lang.Object> predefinedFilter){
        try {
            if (predefinedFilter.get("filter") == null) {
                return new ReturnContent(ReturnContentEnum.PARAMETER_NOT_FOUND.getStatus(), ReturnContentEnum.PARAMETER_NOT_FOUND.getInfo());
            }
            int filterID = (int) predefinedFilter.get("filter");

            if (filterRepository.findOne(filterID) == null){
                return new ReturnContent(ReturnContentEnum.FILTER_NOT_FOUND.getStatus(), ReturnContentEnum.FILTER_NOT_FOUND.getInfo());
            }
            if (!Application.userIntent.getPredefinedFilters().contains(filterID)) {
                Application.userIntent.addPredefinedFilter(filterID);
            } else {
                return new ReturnContent(ReturnContentEnum.FILTER_EXISTED.getStatus(), ReturnContentEnum.FILTER_EXISTED.getInfo());
            }
            return new ReturnContent(ReturnContentEnum.SUCCESS.getStatus(), ReturnContentEnum.SUCCESS.getInfo());
        }
        catch (ClassCastException e){
            return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(), ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
        }
    }

    private String findObjectFieldType(Object object){
        // 属性字段
        if (object.getObject_type() == 1){
            DataField field = dataFieldRepository.findOne(Integer.parseInt(object.getRelated_field()));
            return field.getField_type();
        }
        else{
            // TODO: object_type出错
            return null;
        }
    }

    private boolean isTypeMatch(String type1, String type2){
        if (dbTextTypes.contains(type1.toUpperCase()) && !(dbTextTypes.contains(type2.toUpperCase()))){
            return false;
        } else if (dbNumberTypes.contains(type1.toUpperCase()) && !(dbNumberTypes.contains(type2.toUpperCase()))){
            return false;
        }
        return true;
    }
}
