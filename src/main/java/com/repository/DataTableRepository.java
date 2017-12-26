package com.repository;

import com.entity.DataTable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name="data_table")
@Qualifier("dataTableRepository")
@RepositoryRestResource(path="dataTable")
public interface DataTableRepository extends CrudRepository<DataTable, Integer > {
    DataTable findByTableName(String table_name);
}
