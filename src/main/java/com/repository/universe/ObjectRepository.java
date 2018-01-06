package com.repository.universe;

import com.entity.Object;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Table;

/**
 * Created by Bruinx on 2017/11/30.
 */
@Repository
@Table(name="object")
@Qualifier("objectRepository")
@RepositoryRestResource(path="object")
public interface ObjectRepository extends CrudRepository<Object, Integer>{
}
