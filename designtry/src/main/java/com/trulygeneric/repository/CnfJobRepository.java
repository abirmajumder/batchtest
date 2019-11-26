package com.trulygeneric.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.trulygeneric.model.CnfJob;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource ( path = "cnf_jobs", collectionResourceRel = "cnf_jobs" )
public interface CnfJobRepository extends JpaRepository<CnfJob,Integer> {}