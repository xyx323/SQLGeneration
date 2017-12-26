package com.entity.universe;

import javax.persistence.*;

/**
 * Created by Bruinx on 2017/12/2.
 */
@Entity
@Table(name="data_relation")
public class DataRelation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer data_relation_id;

    private Integer table1_id;

    private Integer table2_id;

    public Integer getData_relation_id() {
        return data_relation_id;
    }

    public void setData_relation_id(Integer data_relation_id) {
        this.data_relation_id = data_relation_id;
    }

    public Integer getTable1_id() {
        return table1_id;
    }

    public void setTable1_id(Integer table1_id) {
        this.table1_id = table1_id;
    }

    public Integer getTable2_id() {
        return table2_id;
    }

    public void setTable2_id(Integer table2_id) {
        this.table2_id = table2_id;
    }
}
