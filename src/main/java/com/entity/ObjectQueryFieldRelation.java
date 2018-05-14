package com.entity;

import javax.persistence.*;

@Entity
@Table(name="object_query_field_relation")
public class ObjectQueryFieldRelation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    @Column(name = "qs_field_id")
    private int qsFieldId;

    @Column(name = "object_id")
    private int objectId;

    public int getQsFieldId() {
        return qsFieldId;
    }

    public void setQsFieldId(int qsFieldId) {
        this.qsFieldId = qsFieldId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }
}
