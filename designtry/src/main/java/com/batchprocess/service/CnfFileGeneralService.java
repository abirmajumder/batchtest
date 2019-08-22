
package com.batchprocess.service ;

import java.util.List;

import com.batchprocess.repository.CnfFileGeneralRepository ;

import org.springframework.stereotype.Service ;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired ;

import com.batchprocess.model.CnfBusinessLine;
import com.batchprocess.model.CnfFileGeneral ;

@Service
public class CnfFileGeneralService extends ServiceBase<CnfFileGeneral> implements ICnfFileGeneralService {

	@Autowired
	private CnfFileGeneralRepository cnfFileGeneralRepository ;

	@Override
	public CnfFileGeneralRepository dao () {
		return cnfFileGeneralRepository ;
	}

	@Override @Transactional(readOnly=true)
	public List<CnfFileGeneral> findByCnfBusinessLine(CnfBusinessLine line) {
		return cnfFileGeneralRepository.findByCnfBusinessLine(line);
	}
}