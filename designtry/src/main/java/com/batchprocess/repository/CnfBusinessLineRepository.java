
package com.batchprocess.repository ;

import com.batchprocess.model.CnfBusinessLine ;
import org.springframework.stereotype.Repository ;
import org.springframework.data.jpa.repository.JpaRepository ;

@Repository
public interface CnfBusinessLineRepository extends JpaRepository<CnfBusinessLine,Integer> {
}