
package com.batchprocess.service ;

import org.springframework.stereotype.Service ;
import org.springframework.beans.factory.annotation.Autowired ;
import com.batchprocess.model.CnfBusinessLine ;
import com.batchprocess.repository.CnfBusinessLineRepository ;

@Service
public class CnfBusinessLineService extends ServiceBase<CnfBusinessLine> implements ICnfBusinessLineService {

	@Autowired
	private CnfBusinessLineRepository cnfBusinessLineRepository ;

	@Override
	public CnfBusinessLineRepository dao () {
		return cnfBusinessLineRepository ;
	}
}