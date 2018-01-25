package com.repository;

import com.entity.DataFoundation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Bruinx on 2017/12/2.
 */

@Repository
@Table(name="data_foundation")
@Qualifier("DataFoundationRepository")
@RepositoryRestResource(path="dataFoundation")
public interface DataFoundationRepository extends CrudRepository<DataFoundation, Integer>{

    List<DataFoundation> findAllByDfName(@Param("dfName") String dfName);

    List<DataFoundation> findAllByDfNameContaining(@Param("dfName") String dfName);
}
