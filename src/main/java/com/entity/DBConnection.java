package com.entity;

import javax.persistence.*;

/**
 * Created by Bruinx on 2017/11/30.
 */

@Entity
@Table(name="db_connection")
public class DBConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dbc_id;

    private String db_name;

    private String db_location;

    private String db_port;

    private String db_account;

    private String db_pwd;
}
