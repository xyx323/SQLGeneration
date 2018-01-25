package com.repository;

import com.entity.DataTable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import javax.xml.crypto.Data;
import java.util.List;

@Repository
@Table(name="data_table")
@Qualifier("dataTableRepository")
@RepositoryRestResource(path="dataTable")
public interface DataTableRepository extends CrudRepository<DataTable, Integer > {

    DataTable findByTableName(String table_name);

    List<DataTable> findAllByTableName(@Param("tableName") String tableName);

    List<DataTable> findAllByTableNameContaining(@Param("tableName") String tableName);

    List<DataTable> findAllByTableDescription(@Param("tableDescription") String tableDescription);

    List<DataTable> findAllByTableDescriptionContaining(@Param("tableDescription") String tableDescription);
}
