package com.entity;

import javax.persistence.*;

@Entity
@Table(name="query_statement")
public class QueryStatement {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "qs_id")
    private int qsId;

    @Column(name = "u_id")
    private int uId;

    @Column(name = "qs")
    private String qs;

    @Column(name = "qs_type")
    private int qsType;

    @Column(name = "qs_description")
    private String qsDescription;

    public int getQsId() {
        return qsId;
    }

    public void setQsId(int qsId) {
        this.qsId = qsId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getQs() {
        return qs;
    }

    public void setQs(String qs) {
        this.qs = qs;
    }

    public int getQsType() {
        return qsType;
    }

    public void setQsType(int qsType) {
        this.qsType = qsType;
    }

    public String getQsDescription() {
        return qsDescription;
    }

    public void setQsDescription(String qsDescription) {
        this.qsDescription = qsDescription;
    }
}
