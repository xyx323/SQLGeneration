package com.repository;

import com.entity.DataField;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name="field")
@Qualifier("fieldRepository")
@RepositoryRestResource(path="dataField")
public interface DataFieldRepository extends CrudRepository<DataField, Integer > {

    List<DataField> findAllByFieldName(@Param("fieldName") String fieldName);
}
