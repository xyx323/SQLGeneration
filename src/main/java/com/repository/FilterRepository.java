package com.repository;

import com.entity.Filter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name="filter")
@Qualifier("filterRepository")
public interface FilterRepository extends CrudRepository<Filter, Integer> {
}
