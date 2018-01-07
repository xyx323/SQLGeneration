package com.controller;

import com.Tool.CommonTool;
import com.domain.*;
import com.domain.Filter;
import com.entity.*;
import com.entity.Object;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
public class GenetateSQLController {
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

    @Autowired
    private DataFieldRepository dataFieldRepository;

    @Autowired
    private ObjectFieldRelationRepository objectFieldRelationRepository;

    private Properties operatorProp = new Properties();
    private Properties joinTypeProp = new Properties();
    private Properties calTypeProp = new Properties();

    @RequestMapping(value = "/generateSQL", method = RequestMethod.POST)
    public GenerateContent generateSQL(@RequestBody UserIntent userIntent) {
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("operator.properties");
            operatorProp.load(in);
            in = this.getClass().getClassLoader().getResourceAsStream("join_type.properties");
            joinTypeProp.load(in);
            in = this.getClass().getClassLoader().getResourceAsStream("aggregation.properties");
            calTypeProp.load(in);
        }
        catch (Exception e){
            return new GenerateContent(ReturnContentEnum.LOAD_PROPERTIES_ERROR, "");
        }

        ReturnContent returnContent;

        // checkObjects
        if (userIntent.getObjects() == null || userIntent.getObjects().size() == 0) {
            return new GenerateContent(ReturnContentEnum.PARAMETER_NOT_FOUND, "");
        }

        returnContent = checkObjects(userIntent.getObjects());
        if (returnContent.getStatus() != ReturnContentEnum.SUCCESS.getStatus()){
            return new GenerateContent(returnContent, "");
        }

//        // setAllFilters
//        if (userIntent.getAllFilters() != null) {
//            returnContent = checkFilters(userIntent.getAllFilters());
//
//            if (returnContent.getStatus() != ReturnContentEnum.SUCCESS.getStatus()){
//                return new GenerateContent(returnContent, "");
//            }
//        }
        // checkFilters -- no need

        // checkPreDefinedFilters
        if (userIntent.getPredefinedFilters() != null) {
            returnContent = checkPredefinedFilters(userIntent.getPredefinedFilters());

            if (returnContent.getStatus() != ReturnContentEnum.SUCCESS.getStatus()){
                return new GenerateContent(returnContent, "");
            }
        }

        // checkDistinct -- no need


        //checkOrders
        if (userIntent.getOrders() != null) {
            returnContent = checkOrders(userIntent.getOrders());

            if (returnContent.getStatus() != ReturnContentEnum.SUCCESS.getStatus()){
                return new GenerateContent(returnContent, "");
            }
        }

        //checkReturnNumber -- no need

        return generateSQLfromUserIntent(userIntent);
    }

    private ReturnContent checkObjects(List<Integer> objects){
            try {
                for (int objectID : objects) {
                    Object objectEntity = objectRepository.findOne(objectID);
                    if (objectEntity == null) {
                        return new ReturnContent(ReturnContentEnum.OBJECT_NOT_FOUND.getStatus(), ReturnContentEnum.OBJECT_NOT_FOUND.getInfo());
                    }
                }
            } catch (ClassCastException e){
                return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(), ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
            }

        return new ReturnContent(ReturnContentEnum.SUCCESS.getStatus(), ReturnContentEnum.SUCCESS.getInfo());
    }

    private ReturnContent checkFilters(List<Filter> filters){
         for(com.domain.Filter filter : filters) {
            if (!filter.isAllFieldFilled()) {
                return new ReturnContent(ReturnContentEnum.PARAMETER_NOT_FOUND.getStatus(), ReturnContentEnum.PARAMETER_NOT_FOUND.getInfo());
            }
            Object objectEntity = objectRepository.findOne(filter.getObject());
            if (objectEntity == null) {
                return new ReturnContent(ReturnContentEnum.OBJECT_NOT_FOUND.getStatus(), ReturnContentEnum.OBJECT_NOT_FOUND.getInfo());
            }
            if (objectEntity.getObject_type() == ObjectTypeEnum.ATTRIBUTE.getType() && filter.getOperandType() == 1) {
                String fieldType = findObjectFieldType(objectEntity);
                if (fieldType != null) {
                    if (filter.getOperand() instanceof List<?>) {
                        List<java.lang.Object> operands = (List<java.lang.Object>) filter.getOperand();
                        for (java.lang.Object o : operands) {
                            if (o instanceof Integer || o instanceof Float || o instanceof Double) {
                                if (!(CommonTool.dbNumberTypes.contains(fieldType.toUpperCase()))) {
                                    return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                                            ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
                                }
                            } else if (o instanceof String) {
                                if (!(CommonTool.dbTextTypes.contains(fieldType.toUpperCase()))) {
                                    return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                                            ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
                                }
                            }
                        }
                    } else if (filter.getOperand() instanceof Integer || filter.getOperand() instanceof Float || filter.getOperand() instanceof Double) {
                        if (!(CommonTool.dbNumberTypes.contains(fieldType.toUpperCase()))) {
                            return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                                    ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
                        }
                    } else if (filter.getOperand() instanceof String) {
                        if (!(CommonTool.dbTextTypes.contains(fieldType.toUpperCase()))) {
                            return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                                    ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
                        }
                    }
                }
            } else if (objectEntity.getObject_type() == ObjectTypeEnum.ATTRIBUTE.getType() && filter.getOperandType() == 2) {
                String fieldType = findObjectFieldType(objectEntity);
                if (fieldType != null) {
                    if (filter.getOperand() instanceof List<?>) {
                        List<java.lang.Object> operands = (List<java.lang.Object>) filter.getOperand();
                        for (java.lang.Object o : operands) {
                            if (o instanceof Integer) {
                                Object operandField = objectRepository.findOne((int) o);
                                if (operandField == null) {
                                    return new ReturnContent(ReturnContentEnum.OBJECT_NOT_FOUND.getStatus(),
                                            ReturnContentEnum.OBJECT_NOT_FOUND.getInfo());
                                }
                                String operandFieldType = findObjectFieldType(operandField);
                                if (operandFieldType != null) {
                                    if (!CommonTool.isTypeMatch(fieldType, operandFieldType)) {
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
                        if (operandField == null) {
                            return new ReturnContent(ReturnContentEnum.OBJECT_NOT_FOUND.getStatus(),
                                    ReturnContentEnum.OBJECT_NOT_FOUND.getInfo());
                        }
                        String operandFieldType = findObjectFieldType(operandField);
                        if (operandFieldType != null) {
                            if (!CommonTool.isTypeMatch(fieldType, operandFieldType)) {
                                return new ReturnContent(ReturnContentEnum.OPERAND_OBJECT_TYPE_ERROR.getStatus(),
                                        ReturnContentEnum.OPERAND_OBJECT_TYPE_ERROR.getInfo());
                            }
                        }
                    } else {
                        return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                                ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
                    }
                }
            } else if (filter.getOperandType() == 3 || filter.getOperandType() == 4) {
                // 当操作数的类型为子查询时判断操作数是否为整数，即子查询的id
                if (filter.getOperand() instanceof List<?>) {
                    List<java.lang.Object> operands = (List<java.lang.Object>) filter.getOperand();
                    for (java.lang.Object o : operands) {
                        if (!(o instanceof Integer)) {
                            return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(),
                                    ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
                        }
                        QueryStatement qs = queryStatementRepository.findOne((int) o);
                        if (qs == null) {
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
        }
        return new ReturnContent(ReturnContentEnum.SUCCESS.getStatus(), ReturnContentEnum.SUCCESS.getInfo());
    }

    private ReturnContent checkPredefinedFilters(List<Integer> predefinedFilters){
        try {
            for (int filterID : predefinedFilters) {
                if (filterRepository.findOne(filterID) == null) {
                    return new ReturnContent(ReturnContentEnum.FILTER_NOT_FOUND.getStatus(), ReturnContentEnum.FILTER_NOT_FOUND.getInfo());
                }
            }
            return new ReturnContent(ReturnContentEnum.SUCCESS.getStatus(), ReturnContentEnum.SUCCESS.getInfo());
        }
        catch (ClassCastException e){
            return new ReturnContent(ReturnContentEnum.PARAMETER_TYPE_ERROR.getStatus(), ReturnContentEnum.PARAMETER_TYPE_ERROR.getInfo());
        }
    }

    private ReturnContent checkOrders(List<Order> orders){
        for (Order order : orders) {
            if (order.getOrder() == null || order.getObject() == null) {
                return new ReturnContent(ReturnContentEnum.PARAMETER_NOT_FOUND.getStatus(), ReturnContentEnum.PARAMETER_NOT_FOUND.getInfo());
            }
            Object o = objectRepository.findOne(order.getObject());
            if (o == null) {
                return new ReturnContent(ReturnContentEnum.OBJECT_NOT_FOUND.getStatus(), ReturnContentEnum.OBJECT_NOT_FOUND.getInfo());
            }
        }

        return new ReturnContent(ReturnContentEnum.SUCCESS.getStatus(), ReturnContentEnum.SUCCESS.getInfo());
    }

    private String findObjectFieldType(Object object){
        // 属性字段
        if (object.getObject_type() == ObjectTypeEnum.ATTRIBUTE.getType()){
            List<ObjectFieldRelation> ofrs = objectFieldRelationRepository.findAllByObjectId(object.getObject_id());
            if (ofrs == null || ofrs.size() != 1){
                return null;
            }
            DataField field = fieldRepository.findOne(ofrs.get(0).getFieldId());
            return field.getField_type();
        }
        else{
            // TODO: object_type出错
            return null;
        }
    }

    private GenerateContent generateSQLfromUserIntent(UserIntent userIntent) {
        List<String> relatedTables = new ArrayList<>();
        List<String> groupByObjects = new ArrayList<>();
//        try {
//            InputStream in = this.getClass().getClassLoader().getResourceAsStream("operator.properties");
//            operatorProp.load(in);
//        }
//        catch (Exception e){
//            return new GenerateContent(ReturnContentEnum.LOAD_PROPERTIES_ERROR, "");
//        }

        // 设置是否返回重复记录
        String resultSQL = "";
        String selectClause = "";
        if (userIntent.getDistinct() == Boolean.TRUE) {
            selectClause += "SELECT DISTINCT ";
        } else {
            selectClause += "SELECT ";
        }

        // 填充查询字段
        List<Integer> oIDs = userIntent.getObjects();
        int validOIDNum = 0;
        for (int oID : oIDs) {
            Object o = objectRepository.findOne(oID);
            String fieldName = oIDtoFieldName(oID, relatedTables);
            if (fieldName != null) {
                if (o.getObject_type() == ObjectTypeEnum.ATTRIBUTE.getType() ||
                        calTypeProp.getProperty(String.valueOf(o.getCal_type())) == null){
                    groupByObjects.add(fieldName);
                }
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
        String whereClause = "";
        if (userIntent.getFilter() != null){
            String filterStatement = parseFilter(userIntent.getFilter(), relatedTables);
            if (filterStatement == null){
                return new GenerateContent(ReturnContentEnum.PARSE_FILTER_ERROR, "");
            }
            whereClause = "WHERE " + parseFilter(userIntent.getFilter(), relatedTables) + " ";
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

        // 填充GROUP BY子句
        String groupByClause = "";
        if (groupByObjects.size() != 0 && groupByObjects.size() < validOIDNum){
            groupByClause = "GROUP BY ";
            for (String attribute : groupByObjects){
                groupByClause = groupByClause.concat(attribute).concat(", ");
            }
            groupByClause = groupByClause.substring(0, groupByClause.length()-2) + " ";
        }

        // 填充排序标准
        String orderClause = "";
        List<Order> orders = userIntent.getOrders();
        if (orders.size() > 0){
            orderClause += "ORDER BY ";

            for (Order order : orders) {
                orderClause = orderClause + oIDtoFieldName(order.getObject(), relatedTables);
                if (order.getOrder() != 1) {
                    orderClause += " DESC";
                }
                orderClause += ", ";
            }
            orderClause = orderClause.substring(0, orderClause.length()-2) + " ";
        }

        // 如果有设置返回记录数量
        String limitClause = "";
        if (userIntent.getReturnNumber() > 0) {
            limitClause = limitClause + "LIMIT 0, " + userIntent.getReturnNumber();
        }

        resultSQL = selectClause + fromClause + whereClause + groupByClause + orderClause + limitClause;

        userIntent = new UserIntent();

        return new GenerateContent(ReturnContentEnum.SUCCESS, resultSQL);
    }

    private String oIDtoFieldName(int oID, List<String> relatedTables){
        Object o = objectRepository.findOne(oID);
        if (o == null){
            return null;
        }
        List<ObjectFieldRelation> ofrs = objectFieldRelationRepository.findAllByObjectId(oID);
        // 属性字段
        if (o.getObject_type() == ObjectTypeEnum.ATTRIBUTE.getType()){
            if (ofrs == null || ofrs.size() != 1){
                return null;
            }
            DataField field = fieldRepository.findOne(ofrs.get(0).getFieldId());
            DataTable dataTable = dataTableRepository.findOne(field.getTable_id());
            if (!relatedTables.contains(dataTable.getTableName())){
                relatedTables.add(dataTable.getTableName());
            }
            // TODO: 没找到对应字段
            return dataTable.getTableName() + "." + field.getField_name();
        }
        // 度量字段
        else if (o.getObject_type() == ObjectTypeEnum.COMPLEX_MEASURE.getType()){
            return parseMeasureObject(o, relatedTables);
        }
        else if (o.getObject_type() == ObjectTypeEnum.AGG_MEASURE.getType()){
            String aggFunction = calTypeProp.getProperty(String.valueOf(o.getCal_type()));
            if (ofrs == null || ofrs.size() != 1 || aggFunction == null){
                return null;
            }
            DataField field = fieldRepository.findOne(ofrs.get(0).getFieldId());
            DataTable dataTable = dataTableRepository.findOne(field.getTable_id());
            if (!relatedTables.contains(dataTable.getTableName())){
                relatedTables.add(dataTable.getTableName());
            }
            return aggFunction + "(" + dataTable.getTableName() + "." + field.getField_name() + ")";
        } else {
            // TODO: object_type出错
            return null;
        }
    }

    private String parseMeasureObject(Object o, List<String> relatedTables){
        String curWord = "";
        String measure = o.getSql_text();
        if (measure == null)
        {
            return null;
        }
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
        String calType = calTypeProp.getProperty(String.valueOf(o.getCal_type()));
        if (calType == null){
            return measure;
        } else {
            return calType + "(" + measure + ")";
        }
    }

    private String getOperator(int operatorID){
        // TODO: 得到操作符
        return operatorProp.getProperty(String.valueOf(operatorID));
    }

    private String getFilterStatement(Filter filter, List<String> relatedTables){
        // 得到操作符
        String fieldName = oIDtoFieldName(filter.getObject(), relatedTables);
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
                                + oIDtoFieldName((int) boundries.get(0), relatedTables) + " AND " + oIDtoFieldName((int) boundries.get(1), relatedTables);
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
                            candiStr = candiStr + oIDtoFieldName((int) candidate, relatedTables) + ", ";
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
                        subFilter = fieldName + " = " + oIDtoFieldName((int) value, relatedTables) + " OR ";
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
                    return fieldName + " " + operator + " " + oIDtoFieldName((int) operand, relatedTables);
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

    private String parseFilter(Filter filter, List<String> relatedTables){
        if (filter.getFilterType() == null){
            return null;
        }
        if (filter.getFilterType() == 2){
            return getFilterStatement(filter, relatedTables);
        } else if (filter.getFilterType() == 3){
            if (filter.getPredefinedFilterID() == null){
                return null;
            }
            com.entity.Filter preFilter = filterRepository.findOne(filter.getPredefinedFilterID());
            String fieldName = oIDtoFieldName(preFilter.getObject_id(), relatedTables);
            String operator = getOperator(preFilter.getOperator());
            // TODO: 处理不同类型的操作数
            String operand = preFilter.getOperands();
            if (fieldName != null && operator != null && operand != null) {
                return fieldName + " " + operator + " " + operand;
            } else {
                return null;
            }
        }
        if (filter.getChildren() == null || filter.getChildren().isEmpty()) {
            return null;
        }
        if (filter.getLogicOperators().size() != filter.getChildren().size() - 1){
            return null;
        }
        String result = "";
        List<String> childStatements = new ArrayList<>();
        for (Filter child : filter.getChildren()){
            String childStatement = parseFilter(child, relatedTables);
            if (childStatement == null){
                return null;
            }
            childStatements.add(childStatement);
        }
        List<Integer> logicOperators = filter.getLogicOperators();
        for (int i = 0; i < logicOperators.size(); i++){
            String operator;
            if (logicOperators.get(i) == 1){
                operator = " AND ";
            }else if (logicOperators.get(i) == 2){
                operator = " OR ";
            } else {
                return null;
            }
            result = result.concat(childStatements.get(i)).concat(operator);
        }
        result = result.concat(childStatements.get(childStatements.size() - 1));
        return "(" + result + ")";
    }
}
