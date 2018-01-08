package com.entity;

import javax.persistence.*;
/**
 * Created by Bruinx on 2017/11/30.
 */
@Entity
@Table(name="universe")
public class Universe {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    @Column(name = "u_id")
    private int uId;

    @Column(name = "df_id")
    private int dfId;

    @Column(name = "dbc_id")
    private int dbcId;

    @Column(name = "universe_name")
    private String universeName;

    @Column(name = "u_creator")
    private String uCreator;

    @Column(name = "u_create_time")
    private String uCreateTime;

    @Column(name = "u_description")
    private String uDescription;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getDfId() {
        return dfId;
    }

    public void setDfId(int dfId) {
        this.dfId = dfId;
    }

    public int getDbcId() {
        return dbcId;
    }

    public void setDbcId(int dbcId) {
        this.dbcId = dbcId;
    }

    public String getuCreator() {
        return uCreator;
    }

    public void setuCreator(String uCreator) {
        this.uCreator = uCreator;
    }

    public String getuCreateTime() {
        return uCreateTime;
    }

    public void setuCreateTime(String uCreateTime) {
        this.uCreateTime = uCreateTime;
    }

    public String getuDescription() {
        return uDescription;
    }

    public void setuDescription(String uDescription) {
        this.uDescription = uDescription;
    }

    public String getUniverseName() {
        return universeName;
    }

    public void setUniverseName(String universeName) {
        this.universeName = universeName;
    }
}

