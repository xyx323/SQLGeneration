package com.entity;

import javax.persistence.*;

@Entity
@Table(name="data_field")
public class DataField {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    @Column(name = "field_id")
    private int fieldId;

    @Column(name = "table_id")
    private int tableId;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "field_type")
    private String fieldType;

    @Column(name = "field_length")
    private int fieldLength;

    @Column(name = "is_pk")
    private boolean isPk;

    @Column(name = "is_fk")
    private boolean isFk;

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(int fieldLength) {
        this.fieldLength = fieldLength;
    }

    public boolean isPk() {
        return isPk;
    }

    public void setPk(boolean pk) {
        this.isPk = pk;
    }

    public boolean isFk() {
        return isFk;
    }

    public void setFk(boolean fk) {
        this.isFk = fk;
    }
}
