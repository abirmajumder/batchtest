
package com.batchprocess.service ;

import java.util.List;

import com.batchprocess.repository.CnfFileGeneralRepository ;
import com.batchprocess.repository.CnfQueryRepository;

import org.springframework.stereotype.Service ;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired ;

import com.batchprocess.model.CnfBusinessLine;
import com.batchprocess.model.CnfFileGeneral ;

@Service
public class CnfFileGeneralService extends ServiceBase<CnfFileGeneral> implements ICnfFileGeneralService {

	@Autowired private CnfFileGeneralRepository cnfFileGeneralRepository ;
	@Autowired private CnfQueryRepository queryRepo;

	@Override
	public CnfFileGeneralRepository dao () {
		return cnfFileGeneralRepository ;
	}

	@Override @Transactional(readOnly=true)
	public List<CnfFileGeneral> findByCnfBusinessLine(CnfBusinessLine line) {
		return cnfFileGeneralRepository.findByCnfBusinessLine(line);
	}
	
	@Override
	public CnfFileGeneral persist(CnfFileGeneral file) {
		queryRepo.save(file.getCnfQuery());
		dao().save(file);
		return file;
	}
}