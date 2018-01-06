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
    private int data_relation_id;

    @Column(name = "field1_id")
    private int field1_id;

    @Column(name = "field2_id")
    private int field2_id;

    @Column(name = "data_relation_mode")
    private int data_relation_mode;

    public int getData_relation_id() {
        return data_relation_id;
    }

    public void setData_relation_id(int data_relation_id) {
        this.data_relation_id = data_relation_id;
    }

    public int getField1_id() {
        return field1_id;
    }

    public void setField1_id(int field1_id) {
        this.field1_id = field1_id;
    }

    public int getField2_id() {
        return field2_id;
    }

    public void setField2_id(int field2_id) {
        this.field2_id = field2_id;
    }

    public int getData_relation_mode() {
        return data_relation_mode;
    }

    public void setData_relation_mode(int data_relation_mode) {
        this.data_relation_mode = data_relation_mode;
    }
}
