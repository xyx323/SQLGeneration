package com.repository;

import com.entity.Field;
import com.entity.Object;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name="field")
@Qualifier("fieldRepository")
public interface FieldRepository extends CrudRepository<Field, Integer > {
}
