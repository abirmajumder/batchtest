
package com.batchprocess.service ;

import org.springframework.stereotype.Service ;
import com.batchprocess.model.CnfFieldMapping ;
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
}