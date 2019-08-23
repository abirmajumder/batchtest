
package com.batchprocess.service ;

import java.util.List;

import com.batchprocess.model.CnfBusinessLine;
import com.batchprocess.model.CnfFileGeneral ;

public interface ICnfFileGeneralService extends IService<CnfFileGeneral> {
	List<CnfFileGeneral> findByCnfBusinessLine( CnfBusinessLine line );
	CnfFileGeneral persist( CnfFileGeneral file );
}