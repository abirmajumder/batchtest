package com.trulygeneric.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.trulygeneric.model.CnfDataSource;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource ( path = "cnf_data_sources", collectionResourceRel = "cnf_data_sources" )
public interface CnfDataSourceRepository extends JpaRepository<CnfDataSource,Integer> {}