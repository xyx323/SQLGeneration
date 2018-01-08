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

    @Column(name = "table_id")
    private int tableId;

    @Column(name = "df_id")
    private int dfId;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "table_description")
    private String tableDescription;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getDfId() {
        return dfId;
    }

    public void setDfId(int dfId) {
        this.dfId = dfId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableDescription() {
        return tableDescription;
    }

    public void setTableDescription(String tableDescription) {
        this.tableDescription = tableDescription;
    }
}