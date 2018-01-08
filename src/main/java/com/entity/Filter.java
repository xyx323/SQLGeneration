package com.entity;

import javax.persistence.*;

@Entity
@Table(name="filter")
public class Filter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "filter_id")
    private int filterId;

    @Column(name = "folder_id")
    private int folderId;

    @Column(name = "object_id")
    private int objectId;

    @Column(name = "filter_name")
    private String filterName;

    @Column(name = "operator")
    private int operator;

    @Column(name = "operand_type")
    private int operandType;

    @Column(name = "operands")
    private String operands;

    public int getFilterId() {
        return filterId;
    }

    public void setFilterId(int filterId) {
        this.filterId = filterId;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public int getOperandType() {
        return operandType;
    }

    public void setOperandType(int operandType) {
        this.operandType = operandType;
    }

    public String getOperands() {
        return operands;
    }

    public void setOperands(String operands) {
        this.operands = operands;
    }
}
