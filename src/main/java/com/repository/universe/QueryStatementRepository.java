package com.repository.universe;

import com.entity.universe.QueryStatement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name="query_statement")
@Qualifier("queryStatementRepository")
@RepositoryRestResource(path="queryStatement")
public interface QueryStatementRepository extends CrudRepository<QueryStatement, Integer> {
}
