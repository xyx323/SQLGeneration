package com.entity;

import javax.persistence.*;

/**
 * Created by Bruinx on 2018/1/6.
 */
@Entity
@Table(name="object_field_relation")
public class ObjectFieldRelation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    @Column(name = "field_id")
    private int fieldId;

    @Column(name = "object_id")
    private int objectId;

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
}
