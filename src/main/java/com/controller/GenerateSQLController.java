package com.controller;

import com.Application;
import com.domain.Filter;
import com.domain.Order;
import com.entity.DataTable;
import com.entity.Field;
import com.entity.Object;
import com.repository.DataTableRepository;
import com.repository.FieldRepository;
import com.repository.FilterRepository;
import com.repository.ObjectRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    private FieldRepository fieldRepository;

    @Autowired
    private DataTableRepository dataTableRepository;

    @Autowired
    private FilterRepository filterRepository;

    private List<String> relatedTables = new ArrayList<>();

    private Properties operatorProp;

    static {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("resource/operator.properties"));
            Properties p = new Properties();
            p.load(in);
            p.getProperty("2");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/generateSQL", method = RequestMethod.GET)
    public String generateSQL() {
        relatedTables.clear();

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

        // 填充过滤条件
        resultSQL += "WHERE ";
        List<Filter> filters = Application.userIntent.getFilterList();
        for (Filter filter : filters) {
            String fieldName = oIDtoFieldName(filter.getObject());
            String operator = getOperator(filter.getOperator());
            // TODO: 处理不同类型的操作数
            String operand = filter.getOperand();
            if (fieldName != null && operator != null && operand != null) {
                resultSQL = resultSQL + fieldName + " " + operator + " "
                        + operand + " AND ";
            } else {
                // TODO: 错误处理
                continue;
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

        // 填充from子句
        resultSQL += "FROM ";
        for (String table : relatedTables) {
            resultSQL = resultSQL + table + ", ";
        }

        // 填充排序标准
        resultSQL += "ORDER BY ";
        List<Order> orders = Application.userIntent.getOrders();
        for (Order order : orders) {
            resultSQL = resultSQL + oIDtoFieldName(order.getObject()) + " ";
            if (order.getOrder() != 1) {
                resultSQL += "DESC ";
            }
            resultSQL += ", ";
        }

        // 如果有设置返回记录数量
        if (Application.userIntent.getReturnNumber() > 0) {
            resultSQL = resultSQL + "LIMIT 0, " + Application.userIntent.getReturnNumber();
        }
        return "SQL statement";
    }

    private String oIDtoFieldName(int oID){
        Object o = objectRepository.findOne(oID);
        // 属性字段
        if (o.getObject_type() == 1){
            Field field = fieldRepository.findOne(Integer.parseInt(o.getRelated_field()));
            DataTable dataTable = dataTableRepository.findOne(field.getTable_id());
            if (!relatedTables.contains(dataTable.getTable_name())){
                relatedTables.add(dataTable.getTable_name());
            }
            // TODO: 没找到对应字段
            return dataTable.getTable_name() + "." + field.getField_name();
        }
        // 度量字段
        else if (oID == 2){
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
}

