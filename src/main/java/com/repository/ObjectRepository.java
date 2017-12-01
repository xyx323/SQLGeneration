package com.repository;

import com.entity.Object;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import javax.persistence.Table;

/**
 * Created by Bruinx on 2017/11/30.
 */
@Repository
@Table(name="object")
@Qualifier("objectRepository")
public interface ObjectRepository extends CrudRepository<Object, Integer>{

    public Object findOne(Integer id);

}
