package com.repository;

import com.entity.Folder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by Bruinx on 2017/12/2.
 */
@Repository
@Table(name = "folder")
@Qualifier("folderRepository")
@RepositoryRestResource(path="folder")
public interface FolderRepository extends CrudRepository<Folder, Integer>{

    List<Folder> findAllByFolderName(@Param("folderName") String folderName);

    List<Folder> findAllByFolderNameContaining(@Param("folderName") String folderName);
}
