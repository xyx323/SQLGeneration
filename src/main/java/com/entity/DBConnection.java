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

    @Column(name = "dbc_id")
    private int dbcId;

    @Column(name = "db_name")
    private String dbName;

    @Column(name = "db_location")
    private String dbLocation;

    @Column(name = "db_port")
    private String dbPort;

    @Column(name = "db_account")
    private String dbAccount;

    @Column(name = "db_pwd")
    private String dbPwd;

    public int getDbcId() {
        return dbcId;
    }

    public void setDbcId(int dbcId) {
        this.dbcId = dbcId;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbLocation() {
        return dbLocation;
    }

    public void setDbLocation(String dbLocation) {
        this.dbLocation = dbLocation;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbAccount() {
        return dbAccount;
    }

    public void setDbAccount(String dbAccount) {
        this.dbAccount = dbAccount;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }
}
