package com.entity;

import javax.persistence.*;
/**
 * Created by Bruinx on 2017/11/30.
 */

@Entity
@Table(name="object")
public class Object {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    @Column(name = "object_id")
    private int object_id;

    @Column(name = "folder_id")
    private int folder_id;

    @Column(name = "object_name")
    private String object_name;

    @Column(name = "object_description")
    private String object_description;

    @Column(name = "object_type")
    private int object_type;

    @Column(name = "cal_type")
    private int cal_type;

    @Column(name = "sql_text")
    private String sql_text;

    //@Column(name="object_id")
    public int getObject_id() {
        return object_id;
    }

    public void setObject_id(int object_id) {
        this.object_id = object_id;
    }

    public int getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(int folder_id) {
        this.folder_id = folder_id;
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name;
    }

    public String getObject_description() {
        return object_description;
    }

    public void setObject_description(String object_description) {
        this.object_description = object_description;
    }

    public int getObject_type() {
        return object_type;
    }

    public void setObject_type(int object_type) {
        this.object_type = object_type;
    }

    public int getCal_type() {
        return cal_type;
    }

    public void setCal_type(int cal_type) {
        this.cal_type = cal_type;
    }

    public String getSql_text() {
        return sql_text;
    }

    public void setSql_text(String sql_text) {
        this.sql_text = sql_text;
    }
   }
