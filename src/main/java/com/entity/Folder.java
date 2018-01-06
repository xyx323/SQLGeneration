package com.entity;

import javax.persistence.*;
/**
 * Created by Bruinx on 2017/11/30.
 */
@Entity
@Table(name="folder")
public class Folder {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int folder_id;

    private int u_id;

    private String folder_name;

    private String folder_description;

    public int getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(int folder_id) {
        this.folder_id = folder_id;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public String getFolder_description() {
        return folder_description;
    }

    public void setFolder_description(String folder_description) {
        this.folder_description = folder_description;
    }
}
