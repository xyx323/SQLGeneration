package com.repository;

import com.entity.Filter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name="filter")
@Qualifier("filterRepository")
@RepositoryRestResource(path="filter")
public interface FilterRepository extends CrudRepository<Filter, Integer> {

    List<Filter> findAllByFilterName(@Param("filterName") String filterName);

    List<Filter> findAllByFilterNameContaining(@Param("filterName") String filterName);
}
