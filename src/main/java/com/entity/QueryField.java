package com.entity;

import javax.persistence.*;

@Entity
@Table(name="query_field")
public class QueryField {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    @Column(name = "qs_field_id")
    private int qsFieldId;

    @Column(name = "qs_id")
    private int qsId;

    @Column(name = "field_name")
    private String fieldName;

    @Column(name = "field_type")
    private String fieldType;

    public int getQsFieldId() {
        return qsFieldId;
    }

    public void setQsFieldId(int qsFieldId) {
        this.qsFieldId = qsFieldId;
    }

    public int getQsId() {
        return qsId;
    }

    public void setQsId(int qsId) {
        this.qsId = qsId;
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
}
