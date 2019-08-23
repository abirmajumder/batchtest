
package com.batchprocess.repository ;

import java.util.List;

import com.batchprocess.model.CnfFieldMapping ;
import com.batchprocess.model.CnfFileGeneral;

import org.springframework.stereotype.Repository ;
import org.springframework.data.jpa.repository.JpaRepository ;

@Repository
public interface CnfFieldMappingRepository extends JpaRepository<CnfFieldMapping,Integer> {
	List<CnfFieldMapping> findByCnfFileGeneral( CnfFileGeneral file);
}