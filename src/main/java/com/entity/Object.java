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
    private int objectId;

    @Column(name = "folder_id")
    private int folderId;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "object_description")
    private String objectDescription;

    @Column(name = "object_type")
    private int objectType;

    @Column(name = "cal_type")
    private int calType;

    @Column(name = "sql_text")
    private String sqlText;

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectDescription() {
        return objectDescription;
    }

    public void setObjectDescription(String objectDescription) {
        this.objectDescription = objectDescription;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public int getCalType() {
        return calType;
    }

    public void setCalType(int calType) {
        this.calType = calType;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }
   }
