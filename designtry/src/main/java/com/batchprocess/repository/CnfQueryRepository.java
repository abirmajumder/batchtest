
package com.batchprocess.repository ;

import com.batchprocess.model.CnfQuery ;
import org.springframework.stereotype.Repository ;
import org.springframework.data.jpa.repository.JpaRepository ;

@Repository
public interface CnfQueryRepository extends JpaRepository<CnfQuery,Integer> {
}