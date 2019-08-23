package com.batchprocess.factory;

import com.batchprocess.model.CnfBusinessLine;
import com.batchprocess.model.CnfFileGeneral;

public class CnfFileGeneralFactory {
	
	public static CnfFileGeneral create( CnfBusinessLine line ) {
		CnfFileGeneral obj = new CnfFileGeneral();
		obj.setCnfBusinessLine(line);
		obj.setCnfQuery(CnfQueryFactory.create());
		obj.setActive("Y");
		obj.setType("Primary");
		obj.setInVouge("Y");
		return obj;
	}
	
}
