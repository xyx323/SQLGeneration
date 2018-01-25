package com.repository;

import com.entity.DataSchema;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "data_schema")
@Qualifier("dataSchemaRepository")
@RepositoryRestResource(path="dataSchema")
public interface DataSchemaRepository extends CrudRepository<DataSchema, Integer > {
    List<DataSchema> findAllBySchemaName(@Param("schemaName") String schemaName);

    List<DataSchema> findAllBySchemaNameContaining(@Param("schemaName") String schemaName);
}
