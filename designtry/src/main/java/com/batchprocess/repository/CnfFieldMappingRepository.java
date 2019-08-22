
package com.batchprocess.repository ;

import com.batchprocess.model.CnfFieldMapping ;
import org.springframework.stereotype.Repository ;
import org.springframework.data.jpa.repository.JpaRepository ;

@Repository
public interface CnfFieldMappingRepository extends JpaRepository<CnfFieldMapping,Integer> {
}