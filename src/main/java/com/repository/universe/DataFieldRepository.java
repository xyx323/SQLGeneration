package com.repository.universe;

import com.entity.universe.DataField;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name="field")
@Qualifier("fieldRepository")
@RepositoryRestResource(path="dataField")
public interface DataFieldRepository extends CrudRepository<DataField, Integer > {
}
