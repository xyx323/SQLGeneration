package com.entity.universe;

import javax.persistence.*;

@Entity
@Table(name="data_field")
public class DataField {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int field_id;

    private int table_id;

    private String field_name;

    private String field_type;

    private int field_length;

    private boolean is_pk;

    private boolean is_fk;

    public int getField_id() {
        return field_id;
    }

    public void setField_id(int field_id) {
        this.field_id = field_id;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getField_type() {
        return field_type;
    }

    public void setField_type(String field_type) {
        this.field_type = field_type;
    }

    public int getField_length() {
        return field_length;
    }

    public void setField_length(int field_length) {
        this.field_length = field_length;
    }

    public boolean isIs_pk() {
        return is_pk;
    }

    public void setIs_pk(boolean is_pk) {
        this.is_pk = is_pk;
    }

    public boolean isIs_fk() {
        return is_fk;
    }

    public void setIs_fk(boolean is_fk) {
        this.is_fk = is_fk;
    }
}
