package com.repository;

import com.entity.ObjectFieldRelation;
import com.entity.ObjectQueryFieldRelation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name="object_query_field_relation")
@Qualifier("objectQueryFieldRelationRepository")
@RepositoryRestResource(path="objectQueryFieldRelation")
public interface ObjectQueryFieldRelationRepository extends CrudRepository<ObjectQueryFieldRelation, Integer > {
    List<ObjectQueryFieldRelation> findAllByQsFieldId(int qsFieldId);

    List<ObjectQueryFieldRelation> findAllByObjectId(@Param("objectId") int objectId);
}
