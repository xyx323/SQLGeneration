package com.entity.universe;

import javax.persistence.*;
/**
 * Created by Bruinx on 2017/11/30.
 */

@Entity
@Table(name="object")
public class Object {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int object_id;

    private int folder_id;

    private String object_name;

    private String object_description;

    private int object_type;

    private String related_field;

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

    public String getRelated_field() {
        return related_field;
    }

    public void setRelated_field(String related_field) {
        this.related_field = related_field;
    }
}
