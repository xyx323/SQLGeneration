package com.repository;

import com.entity.DBConnection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by Bruinx on 2017/12/2.
 */
@Repository
@Table(name = "db_conncetion")
@Qualifier("dbConnectionRepository")
@RepositoryRestResource(path="dbConnection")
public interface DBConnectionRepository extends CrudRepository<DBConnection, Integer > {
}
