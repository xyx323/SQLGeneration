package com.repository;

import com.entity.DataField;
import com.entity.QueryField;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name="query_field")
@Qualifier("queryFieldRepository")
@RepositoryRestResource(path="queryField")
public interface QueryFieldRepository extends CrudRepository<QueryField, Integer > {
    List<QueryField> findAllByFieldName(@Param("fieldName") String fieldName);

    List<QueryField> findAllByFieldNameContaining(@Param("fieldName") String fieldName);
}
