package com.repository;

import com.entity.Object;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by Bruinx on 2017/11/30.
 */
@Repository
@Table(name="object")
@Qualifier("objectRepository")
@RepositoryRestResource(path="object")
public interface ObjectRepository extends CrudRepository<Object, Integer>{

    List<Object> findAllByObjectName(@Param("objectName") String objectName);

    List<Object> findAllByObjectDescription(@Param("objectDescription") String objectDescription);
}
