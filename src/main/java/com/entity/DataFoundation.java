package com.entity;

import javax.persistence.*;

/**
 * Created by Bruinx on 2017/11/30.
 */

@Entity
@Table(name="data_foundation")
public class DataFoundation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    @Column(name = "df_id")
    private int dfId;

    @Column(name = "df_name")
    private String dfName;

    @Column(name = "df_creator")
    private String dfCreator;

    @Column(name = "df_create_time")
    private String dfCreateTime;

    @Column(name = "df_description")
    private String dfDescription;

    public int getDfId() {
        return dfId;
    }

    public void setDfId(int dfId) {
        this.dfId = dfId;
    }

    public String getDfName() {
        return dfName;
    }

    public void setDfName(String dfName) {
        this.dfName = dfName;
    }

    public String getDfCreator() {
        return dfCreator;
    }

    public void setDfCreator(String dfCreator) {
        this.dfCreator = dfCreator;
    }

    public String getDfCreateTime() {
        return dfCreateTime;
    }

    public void setDfCreateTime(String dfCreateTime) {
        this.dfCreateTime = dfCreateTime;
    }

    public String getDfDescription() {
        return dfDescription;
    }

    public void setDfDescription(String dfDescription) {
        this.dfDescription = dfDescription;
    }
}