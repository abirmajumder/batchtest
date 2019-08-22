
package com.batchprocess.repository ;

import java.util.List;

import org.springframework.stereotype.Repository ;
import org.springframework.data.jpa.repository.JpaRepository ;

import com.batchprocess.model.CnfBusinessLine;
import com.batchprocess.model.CnfFileGeneral ;

@Repository
public interface CnfFileGeneralRepository extends JpaRepository<CnfFileGeneral,Integer> {
	List<CnfFileGeneral> findByCnfBusinessLine( CnfBusinessLine line );
}