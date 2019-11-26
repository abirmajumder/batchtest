package com.trulygeneric.repository;

import com.trulygeneric.model.CnfApplication;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource ( path = "cnf_applications", collectionResourceRel = "cnf_applications" )
public interface CnfApplicationRepository extends JpaRepository<CnfApplication,Integer> {}