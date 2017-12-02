package com.repository;

import com.entity.DataRelation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by Bruinx on 2017/12/2.
 */
@Repository
@Table(name = "data_relation")
@Qualifier("dataRelationRepository")
public interface DataRelationRepository extends CrudRepository<DataRelation, Integer > {
}
