package com.repository;

import com.entity.Folder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by Bruinx on 2017/12/2.
 */
@Repository
@Table(name = "folder")
@Qualifier("folderRepository")
public interface FolderRepository extends CrudRepository<Folder, Integer>{
}
