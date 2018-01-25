package com.entity;

import javax.persistence.*;

@Entity
@Table(name="data_schema")
public class DataSchema {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    @Column(name = "schema_id")
    private int schemaId;

    @Column(name = "schema_name")
    private String schemaName;

    public int getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(int schemaId) {
        this.schemaId = schemaId;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }
}
