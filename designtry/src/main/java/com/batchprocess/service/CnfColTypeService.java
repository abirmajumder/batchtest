
package com.batchprocess.service ;

import com.batchprocess.model.CnfColType ;
import com.batchprocess.repository.CnfColTypeRepository ;
import org.springframework.stereotype.Service ;
import org.springframework.beans.factory.annotation.Autowired ;

@Service
public class CnfColTypeService extends ServiceBase<CnfColType> implements ICnfColTypeService {

	@Autowired
	private CnfColTypeRepository cnfColTypeRepository ;

	@Override
	public CnfColTypeRepository dao () {
		return cnfColTypeRepository ;
	}
}