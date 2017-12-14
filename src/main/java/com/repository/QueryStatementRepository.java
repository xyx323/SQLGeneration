package com.repository;

import com.entity.QueryStatement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name="query_statement")
@Qualifier("queryStatementRepository")
public interface QueryStatementRepository extends CrudRepository<QueryStatement, Integer> {
}
