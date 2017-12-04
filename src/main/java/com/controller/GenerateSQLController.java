package com.controller;

import com.Application;
import com.domain.Filter;
import com.domain.Order;
import com.entity.DataTable;
import com.entity.DataField;
import com.entity.Object;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Bruinx on 2017/11/30.
 */
@RestController
public class GenerateSQLController {
    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private DataFieldRepository fieldRepository;

    @Autowired
    private DataTableRepository dataTableRepository;

    @Autowired
    private DataFoundationRepository dataFoundationRepository;

    @Autowired
    private FilterRepository filterRepository;

    private List<String> relatedTables = new ArrayList<>();

    private Properties operatorProp = new Properties();

//    static {
//        try {
//            InputStream in = this.getClass().getClassLoader().getResourceAsStream("operator.properties");
//            Properties p = new Properties();
//            p.load(in);
//            p.getProperty("2");
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @RequestMapping(value = "/generateSQL", method = RequestMethod.GET)
    public String generateSQL() {
        relatedTables.clear();
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("operator.properties");
            operatorProp.load(in);
        }
        catch (Exception e){
            return "operator loading failed";
        }

        // 设置是否返回重复记录
        String resultSQL = "";
        if (Application.userIntent.getDistinct() == 1) {
            resultSQL += "SELECT DISTINCT ";
        } else {
            resultSQL += "SELECT ";
        }

        // 填充查询字段
        List<Integer> oIDs = Application.userIntent.getObjectsIDs();
        for (int oID : oIDs) {
            String fieldName = oIDtoFieldName(oID);
            if (fieldName != null) {
                resultSQL += fieldName;
            } else {
                // TODO: 没找到对应
                continue;
            }
            resultSQL += ", ";
        }
        if (oIDs.size() > 0){
            resultSQL = resultSQL.substring(0, resultSQL.length()-2) + " ";
        }

        // 填充过滤条件
        resultSQL += "WHERE ";
        List<Filter> filters = Application.userIntent.getFilterList();
        for (Filter filter : filters) {
//            String fieldName = oIDtoFieldName(filter.getObject());
//            String operator = getOperator(filter.getOperator());
//            // TODO: 处理不同类型的操作数
//            String operand = (String)filter.getOperand();
//            if (fieldName != null && operator != null && operand != null) {
//                resultSQL = resultSQL + fieldName + " " + operator + " "
//                        + operand + " AND ";
//            } else {
//                // TODO: 错误处理
//                continue;
//            }
            String filterStr = getOperator(filter);
            if (filterStr != null){
                resultSQL = resultSQL + filterStr + " AND ";
            }
        }

        // 填充预过滤条件
        List<Integer> predefinedFilters = Application.userIntent.getPredefinedFilterIds();
        for (int filterID : predefinedFilters){
            com.entity.Filter preFilter = filterRepository.findOne(filterID);
            String fieldName = oIDtoFieldName(preFilter.getObject_id());
            String operator = getOperator(preFilter.getOperator());
            // TODO: 处理不同类型的操作数
            String operand = preFilter.getOperands();
            if (fieldName != null && operator != null && operand != null) {
                resultSQL = resultSQL + fieldName + " " + operator + " "
                        + operand + " AND ";
            } else {
                // TODO: 错误处理
                continue;
            }
        }
        if (filters.size() + predefinedFilters.size() > 0){
            resultSQL = resultSQL.substring(0, resultSQL.length() - 4);
        }

        // 填充from子句
        resultSQL += "FROM ";
        for (String table : relatedTables) {
            resultSQL = resultSQL + table + ", ";
        }
        if (relatedTables.size() > 0){
            resultSQL = resultSQL.substring(0, resultSQL.length()-2) + " ";
        }

        // 填充排序标准
        List<Order> orders = Application.userIntent.getOrders();
        if (orders.size() > 0){
            resultSQL += "ORDER BY ";

            for (Order order : orders) {
                resultSQL = resultSQL + oIDtoFieldName(order.getObject()) + " ";
                if (order.getOrder() != 1) {
                    resultSQL += "DESC ";
                }
                resultSQL += ", ";
            }
            resultSQL = resultSQL.substring(0, resultSQL.length()-2) + " ";
        }

        // 如果有设置返回记录数量
        if (Application.userIntent.getReturnNumber() > 0) {
            resultSQL = resultSQL + "LIMIT 0, " + Application.userIntent.getReturnNumber();
        }
        return resultSQL;
    }

    private String oIDtoFieldName(int oID){
        Object o = objectRepository.findOne(oID);
        // 属性字段
        if (o.getObject_type() == 1){
            DataField field = fieldRepository.findOne(Integer.parseInt(o.getRelated_field()));
            DataTable dataTable = dataTableRepository.findOne(field.getTable_id());
            if (!relatedTables.contains(dataTable.getTable_name())){
                relatedTables.add(dataTable.getTable_name());
            }
            // TODO: 没找到对应字段
            return dataTable.getTable_name() + "." + field.getField_name();
        }
        // 度量字段
        else if (o.getObject_type() == 2){
            return parseMeasureObject(o.getRelated_field());
            // TODO: 解析失败
        }
        else{
            // TODO: object_type出错
            return null;
        }
    }

    private String parseMeasureObject(String measure){
        // TODO: 解析度量对象的公式
        return measure;
    }

    private String getOperator(int operatorID){
        // TODO: 得到操作符
        return operatorProp.getProperty(String.valueOf(operatorID));
    }

    private String getOperator(Filter filter){
        // 得到操作符
        String fieldName = oIDtoFieldName(filter.getObject());
        String operator = operatorProp.getProperty(String.valueOf(filter.getOperator()));
        if (operator == null){
            // TODO: 错误处理 无法得到操作符
        }
        java.lang.Object operand = filter.getOperand();
        switch (filter.getOperator()){
            case 7:
            case 8:
                if (!(operand instanceof List<?>)){
                    // TODO: 错误处理 类型不匹配
                    return null;
                }
                List<java.lang.Object> boundries = (List<java.lang.Object>)operand;
                if (boundries.size() != 2) {
                    // TODO: 错误处理 类型不匹配
                    return null;
                }
                if (boundries.get(0) instanceof String && boundries.get(1) instanceof String){
                    return fieldName + " " + operator + " '" + boundries.get(0) + "' AND '" + boundries.get(1) + "'";
                }
                else{
                    return fieldName + " " + operator + " " + boundries.get(0) + " AND " + boundries.get(1);
                }
            case 9:
            case 10:
                if (!(operand instanceof List<?>)){
                    // TODO: 错误处理 类型不匹配
                    return null;
                }
                List<java.lang.Object> candidates = (List<java.lang.Object>)operand;
                String candiStr = "( ";
                if (candidates.size() == 0){
                    // TODO: 错误处理 类型不匹配
                    return null;
                }
                for (java.lang.Object candidate : candidates){
                    if (candidate instanceof String){
                        candiStr = candiStr + "'" + candidate + "', ";
                    }
                    else if (candidate instanceof Boolean){
                        if ((boolean)candidate){
                            candiStr = candiStr + "1, " ;
                        }
                        else {
                            candiStr = candiStr + "0, " ;
                        }
                    }
                    else{
                        candiStr = candiStr + candidate + ", ";
                    }
                }
                candiStr = candiStr.substring(0, candiStr.length() - 2) + " )";
                return fieldName + " " + operator + " " + candiStr;
            case 11:
            case 12:
                return fieldName + " " + operator;
            case 13:
            case 14:
                if (!(operand instanceof String)){
                    // TODO: 错误处理 类型不匹配
                    return null;
                }
                return fieldName + " " + operator + " '" + (String) operand + "'";
            case 15:
                if (!(operand instanceof List<?>)){
                    // TODO: 错误处理 类型不匹配
                    return null;
                }
                List<java.lang.Object> allValues = (List<java.lang.Object>) operand;
                if (allValues.size() == 0){
                    // TODO: 错误处理 类型不匹配
                    return null;
                }
                String resultFilter = "( ";
                for (java.lang.Object value : allValues){
                    String subFilter = "";
                    if (value instanceof String){
                        subFilter = fieldName + " = " + "'" + value + "' OR ";
                    }
                    else if (value instanceof Boolean){
                        if ((boolean)value){
                            subFilter = fieldName + " = 1 OR " ;
                        }
                        else {
                            subFilter = fieldName + " = 0 OR " ;
                        }
                    }
                    else{
                        subFilter = fieldName + " = " + value + " OR ";
                    }
                    resultFilter += subFilter;
                }

                resultFilter = resultFilter.substring(0, resultFilter.length() - 3) + ")";
                return resultFilter;
            default:
                if (operand instanceof String){
                    return fieldName + " " + operator + " '" + operand + "'";
                }
                else if(operand instanceof Boolean){
                    if ((boolean)operand){
                        return fieldName + " = 1";
                    }
                    else {
                        return fieldName + " = 0";
                    }
                }
                else{
                    return fieldName + " " + operator + " " + operand;
                }
        }
    }

//    private String getPredefinedFilter(int filterID){
//        Gson gson = new Gson();
//
//        com.entity.Filter preFilter = filterRepository.findOne(filterID);
//        String fieldName = oIDtoFieldName(preFilter.getObject_id());
//        String operator = operatorProp.getProperty(String.valueOf(preFilter.getOperator()));
//        if (operator == null){
//            // TODO: 错误处理 无法得到操作符
//        }
//        String operand = preFilter.getOperands();
//        switch (preFilter.getOperator()){
//            case 7:
//            case 8:
//                List<java.lang.Object> boundries = gson.fromJson(operand, new TypeToken<List<java.lang.Object>>() {}.getType());
//                if (boundries.size() != 2) {
//                    // TODO: 错误处理 类型不匹配
//                    return null;
//                }
//                if (boundries.get(0) instanceof String && boundries.get(1) instanceof String){
//                    return fieldName + " " + operator + " '" + boundries.get(0) + "' AND '" + boundries.get(1) + "'";
//                }
//                else{
//                    return fieldName + " " + operator + " " + boundries.get(0) + " AND " + boundries.get(1);
//                }
//            case 9:
//            case 10:
//                List<java.lang.Object> candidates = gson.fromJson(operand, new TypeToken<List<java.lang.Object>>() {}.getType());
//                String candiStr = "( ";
//                if (candidates.size() == 0){
//                    // TODO: 错误处理 类型不匹配
//                    return null;
//                }
//                for (java.lang.Object candidate : candidates){
//                    if (candidate instanceof String){
//                        candiStr = candiStr + "'" + candidate + "', ";
//                    }
//                    else if (candidate instanceof Boolean){
//                        if ((boolean)candidate){
//                            candiStr = candiStr + "1, " ;
//                        }
//                        else {
//                            candiStr = candiStr + "0, " ;
//                        }
//                    }
//                    else{
//                        candiStr = candiStr + candidate + ", ";
//                    }
//                }
//                candiStr = candiStr.substring(0, candiStr.length() - 2) + " )";
//                return fieldName + " " + operator + " " + candiStr;
//            case 11:
//            case 12:
//                return fieldName + " " + operator;
//            case 13:
//            case 14:
//                java.lang.Object parsedOperand = gson.fromJson()
//                return fieldName + " " + operator + " '" + operand + "'";
//            case 15:
//                List<java.lang.Object> allValues = gson.fromJson(operand, new TypeToken<List<java.lang.Object>>() {}.getType());
//                if (allValues.size() == 0){
//                    // TODO: 错误处理 类型不匹配
//                    return null;
//                }
//                String resultFilter = "( ";
//                for (java.lang.Object value : allValues){
//                    String subFilter = "";
//                    if (value instanceof String){
//                        subFilter = fieldName + " = " + "'" + value + "' OR ";
//                    }
//                    else if (value instanceof Boolean){
//                        if ((boolean)value){
//                            subFilter = fieldName + " = 1 OR " ;
//                        }
//                        else {
//                            subFilter = fieldName + " = 0 OR " ;
//                        }
//                    }
//                    else{
//                        subFilter = fieldName + " = " + value + " OR ";
//                    }
//                    resultFilter += subFilter;
//                }
//
//                resultFilter = resultFilter.substring(0, resultFilter.length() - 3) + ")";
//                return resultFilter;
//            default:
//                else if(operand instanceof Boolean){
//                    if ((boolean)operand){
//                        return fieldName + " = 1";
//                    }
//                    else {
//                        return fieldName + " = 0";
//                    }
//                }
//                else{
//                    return fieldName + " " + operator + " " + operand;
//                }
//        }
//    }
}

