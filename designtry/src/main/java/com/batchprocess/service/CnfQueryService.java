
package com.batchprocess.service ;

import org.springframework.stereotype.Service ;
import com.batchprocess.repository.CnfQueryRepository ;
import org.springframework.beans.factory.annotation.Autowired ;
import com.batchprocess.model.CnfQuery ;

@Service
public class CnfQueryService extends ServiceBase<CnfQuery> implements ICnfQueryService {

	@Autowired
	private CnfQueryRepository cnfQueryRepository ;

	@Override
	public CnfQueryRepository dao () {
		return cnfQueryRepository ;
	}
}