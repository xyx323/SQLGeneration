package com.domain;

/**
 * Created by Bruinx on 2017/12/4.
 */
public class ReturnContent {
    private int status;

    private String info;

    public ReturnContent() {
    }

    public ReturnContent(int status, String info) {
        this.status = status;
        this.info = info;
    }

    public ReturnContent(ReturnContentEnum returnContentEnum) {
        this.status = returnContentEnum.getStatus();
        this.info = returnContentEnum.getInfo();
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
}
