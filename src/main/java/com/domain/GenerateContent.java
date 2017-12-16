package com.domain;

public class GenerateContent {
    private int status;

    private String info;

    private String SQL;

    public GenerateContent(int status, String info, String SQL) {
        this.status = status;
        this.info = info;
        this.SQL = SQL;
    }

    public GenerateContent(ReturnContentEnum returnContentEnum, String SQL) {
        this.status = returnContentEnum.getStatus();
        this.info = returnContentEnum.getInfo();
        this.SQL = SQL;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSQL() {
        return SQL;
    }

    public void setSQL(String SQL) {
        this.SQL = SQL;
    }
}
