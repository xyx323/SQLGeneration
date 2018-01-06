package com.controller;

import com.Application;
import com.domain.*;
import com.domain.Filter;
import com.entity.DataTable;
import com.entity.DataField;
import com.entity.Object;
import com.entity.QueryStatement;
import com.repository.universe.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.*;

/**
 * Created by Bruinx on 2017/11/30.
 */
@RestController
public class GenerateSQLByStepController {
    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private DataFieldRepository fieldRepository;

    @Autowired
    private DataTableRepository dataTableRepository;

    @Autowired
    private DataFoundationRepository dataFoundationRepository;

    @Autowired
    private QueryStatementRepository queryStatementRepository;

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

    @RequestMapping(value = "/generateSQLByStep", method = RequestMethod.GET)
    public GenerateContent generateSQLByStep() {
        relatedTables.clear();
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("operator.properties");
            operatorProp.load(in);
        }
        catch (Exception e){
            return new GenerateContent(ReturnContentEnum.LOAD_PROPERTIES_ERROR, "");
        }

        // 设置是否返回重复记录
        String resultSQL = "";
        String selectClause = "";
        if (Application.userIntent.getDistinct() == Boolean.TRUE) {
            selectClause += "SELECT DISTINCT ";
        } else {
            selectClause += "SELECT ";
        }

        // 填充查询字段
        List<Integer> oIDs = Application.userIntent.getObjects();
        int validOIDNum = 0;
        for (int oID : oIDs) {
            String fieldName = oIDtoFieldName(oID);
            if (fieldName != null) {
                validOIDNum ++;
                selectClause += fieldName;
            } else {
                // TODO: 没找到对应
                continue;
            }
            selectClause += ", ";
        }
        if (oIDs.size() > 0 && validOIDNum > 0){
            selectClause = selectClause.substring(0, selectClause.length()-2) + " ";
        } else {
            selectClause = "";
        }

        // 填充过滤条件
        String whereClause = "WHERE ";
        int validFilterNum = 0;
        List<Filter> filters = Application.userIntent.getAllFilters();
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
                whereClause = whereClause + filterStr + " AND ";
                validFilterNum ++;
            }
        }

        // 填充预过滤条件
        List<Integer> predefinedFilters = Application.userIntent.getPredefinedFilters();
        for (int filterID : predefinedFilters){
            com.entity.Filter preFilter = filterRepository.findOne(filterID);
            String fieldName = oIDtoFieldName(preFilter.getObject_id());
            String operator = getOperator(preFilter.getOperator());
            // TODO: 处理不同类型的操作数
            String operand = preFilter.getOperands();
            if (fieldName != null && operator != null && operand != null) {
                whereClause = whereClause + fieldName + " " + operator + " "
                        + operand + " AND ";
                validFilterNum ++;
            } else {
                // TODO: 错误处理
                continue;
            }
        }
        if (filters.size() + predefinedFilters.size() > 0 && validFilterNum > 0){
            whereClause = whereClause.substring(0, whereClause.length() - 4);
        }else {
            whereClause = "";
        }

        // 填充from子句
        String fromClause = "FROM ";
        for (String table : relatedTables) {
            fromClause = fromClause + table + " JOIN ";
        }
        if (relatedTables.size() > 0){
            fromClause = fromClause.substring(0, fromClause.length()-6) + " ";
        }else{
            fromClause = "";
        }

        // 填充排序标准
        String orderClause = "";
        List<Order> orders = Application.userIntent.getOrders();
        if (orders.size() > 0){
            orderClause += "ORDER BY ";

            for (Order order : orders) {
                orderClause = orderClause + oIDtoFieldName(order.getObject()) + " ";
                if (order.getOrder() != 1) {
                    orderClause += "DESC ";
                }
                orderClause += ", ";
            }
            orderClause = orderClause.substring(0, orderClause.length()-2) + " ";
        }

        // 如果有设置返回记录数量
        String limitClause = "";
        if (Application.userIntent.getReturnNumber() > 0) {
            limitClause = limitClause + "LIMIT 0, " + Application.userIntent.getReturnNumber();
        }

        resultSQL = selectClause + fromClause + whereClause + orderClause + limitClause;

        Application.userIntent = new UserIntent();

        return new GenerateContent(ReturnContentEnum.SUCCESS, resultSQL);
    }

    private String oIDtoFieldName(int oID){
        Object o = objectRepository.findOne(oID);
        // 属性字段
        if (o.getObject_type() == 1){
            DataField field = fieldRepository.findOne(Integer.parseInt(o.getSql_text()));
            DataTable dataTable = dataTableRepository.findOne(field.getTable_id());
            if (!relatedTables.contains(dataTable.getTableName())){
                relatedTables.add(dataTable.getTableName());
            }
            // TODO: 没找到对应字段
            return dataTable.getTableName() + "." + field.getField_name();
        }
        // 度量字段
        else if (o.getObject_type() == 2){
            return parseMeasureObject(o.getSql_text());
        }
        else{
            // TODO: object_type出错
            return null;
        }
    }

    private String parseMeasureObject(String measure){
        String curWord = "";
        for (int i = 0; i < measure.length(); i++){
            if (Character.isLetterOrDigit(measure.charAt(i)) || measure.charAt(i) == '_'){
                curWord += measure.charAt(i);
            } else {
                if (measure.charAt(i) == '.'){
                    DataTable dataTable = dataTableRepository.findByTableName(curWord);
                    if (dataTable != null){
                        if (!(relatedTables.contains(curWord))){
                            relatedTables.add(curWord);
                        }
                    }
                }
                curWord = "";
            }
        }
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
            return null;
        }

        int type = filter.getOperandType();
        java.lang.Object operand = filter.getOperand();
        switch (filter.getOperator()) {
            case 7:
            case 8:
                if (!(operand instanceof List<?>)) {
                    // TODO: 错误处理 类型不匹配
                    return null;
                }
                List<java.lang.Object> boundries = (List<java.lang.Object>) operand;
                if (boundries.size() != 2) {
                    // TODO: 错误处理 类型不匹配
                    return null;
                }
                if (type == 1) {
                    if (boundries.get(0) instanceof String && boundries.get(1) instanceof String) {
                        return fieldName + " " + operator + " '" + boundries.get(0) + "' AND '" + boundries.get(1) + "'";
                    } else {
                        return fieldName + " " + operator + " " + boundries.get(0) + " AND " + boundries.get(1);
                    }
                }
                else if (type == 2) {
                    if (boundries.get(0) instanceof Integer && boundries.get(1) instanceof Integer) {
                        return fieldName + " " + operator + " "
                                + oIDtoFieldName((int) boundries.get(0)) + " AND " + oIDtoFieldName((int) boundries.get(1));
                    } else {
                        // TODO: 错误处理 类型不匹配
                        return null;
                    }
                } else if (type == 3 || type == 4){
                    if (boundries.get(0) instanceof Integer && boundries.get(1) instanceof Integer) {
                        QueryStatement queryStatement1 = queryStatementRepository.findOne((int) boundries.get(0));
                        QueryStatement queryStatement2 = queryStatementRepository.findOne((int) boundries.get(1));
                        if (queryStatement1 == null || queryStatement2 == null){
                            return null;
                        }
                        return fieldName + " " + operator + " ("
                                + queryStatement1.getQs()+ ") AND (" + queryStatement2.getQs() + ")";
                    } else {
                        // TODO: 错误处理 类型不匹配
                        return null;
                    }
                }
                else {
                    // TODO: 处理其他类型的操作数
                    return null;
                }
            case 9:
            case 10:
                if (!(operand instanceof List<?>)) {
                    // TODO: 错误处理 类型不匹配
                    return null;
                }
                List<java.lang.Object> candidates = (List<java.lang.Object>) operand;
                String candiStr = "( ";
                if (candidates.size() == 0) {
                    // TODO: 错误处理 无候选值
                    return null;
                }
                if (type == 1) {
                    for (java.lang.Object candidate : candidates) {
                        if (candidate instanceof String) {
                            candiStr = candiStr + "'" + candidate + "', ";
                        } else if (candidate instanceof Boolean) {
                            if ((boolean) candidate) {
                                candiStr = candiStr + "1, ";
                            } else {
                                candiStr = candiStr + "0, ";
                            }
                        } else {
                            candiStr = candiStr + candidate + ", ";
                        }
                    }
                }
                else if (type == 2){
                    for (java.lang.Object candidate : candidates) {
                        if (!(candidate instanceof Integer)) {
                            // TODO: 错误处理 类型不匹配
                            return null;
                        }
                        else {
                            candiStr = candiStr + oIDtoFieldName((int) candidate) + ", ";
                        }
                    }
                }else if (type == 3 || type == 4){
                    for (java.lang.Object candidate : candidates) {
                        if (!(candidate instanceof Integer)) {
                            // TODO: 错误处理 类型不匹配
                            return null;
                        }
                        else {
                            QueryStatement queryStatement1 = queryStatementRepository.findOne((int) candidate);
                            if (queryStatement1 == null){
                                continue;
                            }
                            candiStr = candiStr.concat(" (" + queryStatement1.getQs() + "), ");
                        }
                    }
                }
                else {
                    // TODO: 处理其他类型的操作数
                    return null;
                }
                candiStr = candiStr.substring(0, candiStr.length() - 2) + " )";
                return fieldName + " " + operator + " " + candiStr;
            case 11:
            case 12:
                return fieldName + " " + operator;
            case 13:
            case 14:
                if (!(operand instanceof String) || type != 1) {
                    // TODO: 错误处理 类型不匹配
                    return null;
                }
                return fieldName + " " + operator + " '" + (String) operand + "'";
            case 15:
                if (!(operand instanceof List<?>)) {
                    // TODO: 错误处理 类型不匹配
                    return null;
                }
                List<java.lang.Object> allValues = (List<java.lang.Object>) operand;
                if (allValues.size() == 0) {
                    // TODO: 错误处理 无操作数
                    return null;
                }
                String resultFilter = "( ";
                if (type == 1) {
                    for (java.lang.Object value : allValues) {
                        String subFilter = "";
                        if (value instanceof String) {
                            subFilter = fieldName + " = " + "'" + value + "' OR ";
                        } else if (value instanceof Boolean) {
                            if ((boolean) value) {
                                subFilter = fieldName + " = 1 OR ";
                            } else {
                                subFilter = fieldName + " = 0 OR ";
                            }
                        } else {
                            subFilter = fieldName + " = " + value + " OR ";
                        }
                        resultFilter += subFilter;
                    }
                }
                else if (type == 2){
                    for (java.lang.Object value : allValues) {
                        String subFilter = "";
                        if (!(value instanceof Integer)) {
                            // TODO: 错误处理 类型不匹配
                            return null;
                        }
                        subFilter = fieldName + " = " + oIDtoFieldName((int) value) + " OR ";
                        resultFilter += subFilter;
                    }
                } else if (type == 3 || type == 4){
                    for (java.lang.Object value : allValues) {
                        String subFilter = "";
                        if (!(value instanceof Integer)) {
                            // TODO: 错误处理 类型不匹配
                            return null;
                        }
                        QueryStatement queryStatement1 = queryStatementRepository.findOne((int) value);
                        subFilter = fieldName + " = (" + queryStatement1.getQs() + ") OR ";
                        resultFilter = resultFilter.concat(subFilter);
                    }
                }
                else{
                    // TODO: 处理其他类型的操作数
                    return null;
                }

                resultFilter = resultFilter.substring(0, resultFilter.length() - 3) + ")";
                return resultFilter;
            default:
                if (type == 1) {
                    if (operand instanceof String) {
                        return fieldName + " " + operator + " '" + operand + "'";
                    } else if (operand instanceof Boolean) {
                        if ((boolean) operand) {
                            return fieldName + " = 1";
                        } else {
                            return fieldName + " = 0";
                        }
                    } else {
                        return fieldName + " " + operator + " " + operand;
                    }
                }
                else if (type == 2){
                    if (!(operand instanceof Integer)) {
                        // TODO: 错误处理 类型不匹配
                        return null;
                    }
                    return fieldName + " " + operator + " " + oIDtoFieldName((int) operand);
                } else if (type == 3 || type == 4){
                    if (!(operand instanceof Integer)) {
                        // TODO: 错误处理 类型不匹配
                        return null;
                    }
                    QueryStatement queryStatement1 = queryStatementRepository.findOne((int) operand);
                    return fieldName + " " + operator + " (" + queryStatement1.getQs() + ")";
                }
                else{
                    // TODO: 处理其他类型的操作数
                    return null;
                }
        }
    }

//    private String getPredefinedFilter(int filterID){
//        Gson gson = new Gson();
//
//        Filter preFilter = filterRepository.findOne(filterID);
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

