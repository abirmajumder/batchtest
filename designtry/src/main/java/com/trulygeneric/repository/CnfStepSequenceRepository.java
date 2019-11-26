package com.trulygeneric.repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import com.trulygeneric.model.CnfStepSequence;

@RepositoryRestResource ( path = "cnf_step_sequences", collectionResourceRel = "cnf_step_sequences" )
public interface CnfStepSequenceRepository extends JpaRepository<CnfStepSequence,Integer> {}