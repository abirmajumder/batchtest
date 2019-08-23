
package com.batchprocess.service ;

import java.util.List;

import org.springframework.stereotype.Service ;
import org.springframework.transaction.annotation.Transactional;

import com.batchprocess.model.CnfFieldMapping ;
import com.batchprocess.model.CnfFileGeneral;
import com.batchprocess.repository.CnfFieldMappingRepository ;

import org.springframework.beans.factory.annotation.Autowired ;

@Service
public class CnfFieldMappingService extends ServiceBase<CnfFieldMapping> implements ICnfFieldMappingService {

	@Autowired
	private CnfFieldMappingRepository cnfFieldMappingRepository ;

	@Override
	public CnfFieldMappingRepository dao () {
		return cnfFieldMappingRepository ;
	}

	@Override @Transactional ( readOnly = true )
	public List<CnfFieldMapping> findByCnfFileGeneral(CnfFileGeneral file) {
		return cnfFieldMappingRepository.findByCnfFileGeneral(file);
	}
	
	
}