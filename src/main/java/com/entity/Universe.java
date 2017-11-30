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
    private int u_id;

    private int df_id;

    private int dbc_id;

    private String u_creator;

    private String u_create_time;

    private String u_description;

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getDf_id() {
        return df_id;
    }

    public void setDf_id(int df_id) {
        this.df_id = df_id;
    }

    public int getDbc_id() {
        return dbc_id;
    }

    public void setDbc_id(int dbc_id) {
        this.dbc_id = dbc_id;
    }

    public String getU_creator() {
        return u_creator;
    }

    public void setU_creator(String u_creator) {
        this.u_creator = u_creator;
    }

    public String getU_create_time() {
        return u_create_time;
    }

    public void setU_create_time(String u_create_time) {
        this.u_create_time = u_create_time;
    }

    public String getU_description() {
        return u_description;
    }

    public void setU_description(String u_description) {
        this.u_description = u_description;
    }
}

