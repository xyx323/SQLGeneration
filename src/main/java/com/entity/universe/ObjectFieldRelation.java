package com.entity.universe;

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
    private int field_id;

    @Column(name = "object_id")
    private int object_id;

    public int getField_id() {
        return field_id;
    }

    public void setField_id(int field_id) {
        this.field_id = field_id;
    }

    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }
}
