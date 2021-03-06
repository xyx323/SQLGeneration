package com.repository;

import com.entity.DataFoundation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import javax.persistence.Table;

/**
 * Created by Bruinx on 2017/12/2.
 */

@Repository
@Table(name="data_foundation")
@Qualifier("DataFoundationRepository")
public interface DataFoundationRepository extends CrudRepository<DataFoundation, Integer>{
}
