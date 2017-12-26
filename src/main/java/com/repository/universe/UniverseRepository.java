package com.repository.universe;

import com.entity.universe.Universe;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by Bruinx on 2017/12/2.
 */
@Repository
@Table(name = "universe")
@Qualifier("universeRepository")
@RepositoryRestResource(path="universe")
public interface UniverseRepository extends CrudRepository<Universe, Integer> {
}


