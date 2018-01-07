package com.repository;

import com.entity.ObjectFieldRelation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.List;

/**
 * Created by Bruinx on 2018/1/6.
 */
@Repository
@Table(name="object_field_relation")
@Qualifier("objectFieldRelationRepository")
@RepositoryRestResource(path="objectFieldRelation")
public interface ObjectFieldRelationRepository extends CrudRepository<ObjectFieldRelation, Integer> {
    List<ObjectFieldRelation> findAllByFieldId(int fieldId);

    List<ObjectFieldRelation> findAllByObjectId(int objectId);
}
