package com.entity.universe;

import javax.persistence.*;

/**
 * Created by Bruinx on 2017/11/30.
 */

@Entity
@Table(name="data_foundation")
public class DataFoundation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int df_id;

    private String df_name;

    private String df_creator;

    private String df_create_time;

    private String df_description;

}