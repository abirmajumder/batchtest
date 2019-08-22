
package com.batchprocess.repository ;

import com.batchprocess.model.CnfColType ;
import org.springframework.stereotype.Repository ;
import org.springframework.data.jpa.repository.JpaRepository ;

@Repository
public interface CnfColTypeRepository extends JpaRepository<CnfColType,Integer> {
}