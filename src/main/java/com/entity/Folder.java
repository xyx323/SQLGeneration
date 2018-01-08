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

    @Column(name = "folder_id")
    private int folderId;

    @Column(name = "u_id")
    private int uId;

    @Column(name = "folder_name")
    private String folderName;

    @Column(name = "folder_description")
    private String folderDescription;

    public int getFolderId() {
        return folderId;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderDescription() {
        return folderDescription;
    }

    public void setFolderDescription(String folderDescription) {
        this.folderDescription = folderDescription;
    }
}
