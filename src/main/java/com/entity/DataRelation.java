package com.entity;

import javax.persistence.*;

/**
 * Created by Bruinx on 2017/12/2.
 */
@Entity
@Table(name="data_relation")
public class DataRelation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    @Column(name = "data_relation_id")
    private int dataRelationId;

    @Column(name = "field1_id")
    private int field1Id;

    @Column(name = "field2_id")
    private int field2Id;

    @Column(name = "data_relation_mode")
    private int dataRelationMode;

    public int getDataRelationId() {
        return dataRelationId;
    }

    public void setDataRelationId(int dataRelationId) {
        this.dataRelationId = dataRelationId;
    }

    public int getField1Id() {
        return field1Id;
    }

    public void setField1Id(int field1Id) {
        this.field1Id = field1Id;
    }

    public int getField2Id() {
        return field2Id;
    }

    public void setField2Id(int field2Id) {
        this.field2Id = field2Id;
    }

    public int getDataRelationMode() {
        return dataRelationMode;
    }

    public void setDataRelationMode(int dataRelationMode) {
        this.dataRelationMode = dataRelationMode;
    }
}
