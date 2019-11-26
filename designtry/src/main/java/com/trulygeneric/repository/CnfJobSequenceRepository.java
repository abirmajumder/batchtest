package com.trulygeneric.repository;

import com.trulygeneric.model.CnfJobSequence;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource ( path = "cnf_job_sequences", collectionResourceRel = "cnf_job_sequences" )
public interface CnfJobSequenceRepository extends JpaRepository<CnfJobSequence,Integer> {}