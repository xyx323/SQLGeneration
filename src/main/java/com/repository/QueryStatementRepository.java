package com.repository;

import com.entity.QueryStatement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name="query_statement")
@Qualifier("queryStatementRepository")
@RepositoryRestResource(path="queryStatement")
public interface QueryStatementRepository extends CrudRepository<QueryStatement, Integer> {

    List<QueryStatement> findAllByQsDescription(@Param("qsDescription") String qsDescription);

    List<QueryStatement> findAllByQsDescriptionContaining(@Param("qsDescription") String qsDescription);
}
