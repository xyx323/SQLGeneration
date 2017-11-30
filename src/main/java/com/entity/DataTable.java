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

}