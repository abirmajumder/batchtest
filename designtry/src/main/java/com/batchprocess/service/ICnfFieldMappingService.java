
package com.batchprocess.service ;

import java.util.List;

import com.batchprocess.model.CnfFieldMapping ;
import com.batchprocess.model.CnfFileGeneral;

public interface ICnfFieldMappingService extends IService<CnfFieldMapping> {
	List<CnfFieldMapping> findByCnfFileGeneral( CnfFileGeneral file);
}