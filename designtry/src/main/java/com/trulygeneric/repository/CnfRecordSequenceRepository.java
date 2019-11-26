package com.trulygeneric.repository;

import com.trulygeneric.model.CnfRecordSequence;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;

@RepositoryRestResource ( path = "cnf_record_sequences", collectionResourceRel = "cnf_record_sequences" )
public interface CnfRecordSequenceRepository extends JpaRepository<CnfRecordSequence,Integer> {}