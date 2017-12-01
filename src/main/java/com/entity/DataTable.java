package com.entity;

import javax.persistence.*;

/**
 * Created by Bruinx on 2017/11/30.
 */

@Entity
@Table(name="data_table")
public class DataTable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private int table_id;

    private int df_id;

    private String table_name;

    private String table_description;

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getDf_id() {
        return df_id;
    }

    public void setDf_id(int df_id) {
        this.df_id = df_id;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getTable_description() {
        return table_description;
    }

    public void setTable_description(String table_description) {
        this.table_description = table_description;
    }
}