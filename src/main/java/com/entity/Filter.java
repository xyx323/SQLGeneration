package com.entity;

import javax.persistence.*;

@Entity
@Table(name="filter")
public class Filter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int filter_id;

    private int folder_id;

    private int object_id;

    private String filter_name;

    private int operator;

    private int operator_type;

    private String operands;

    public int getFilter_id() {
        return filter_id;
    }

    public void setFilter_id(int filter_id) {
        this.filter_id = filter_id;
    }

    public int getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(int folder_id) {
        this.folder_id = folder_id;
    }

    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public String getFilter_name() {
        return filter_name;
    }

    public void setFilter_name(String filter_name) {
        this.filter_name = filter_name;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public int getOperator_type() {
        return operator_type;
    }

    public void setOperator_type(int operator_type) {
        this.operator_type = operator_type;
    }

    public String getOperands() {
        return operands;
    }

    public void setOperands(String operands) {
        this.operands = operands;
    }
}
