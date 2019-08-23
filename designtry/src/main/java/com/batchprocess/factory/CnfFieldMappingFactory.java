package com.batchprocess.factory;

import com.batchprocess.model.CnfColType;
import com.batchprocess.model.CnfFieldMapping;
import com.batchprocess.model.CnfFileGeneral;

public class CnfFieldMappingFactory {

	public static CnfFieldMapping create( CnfFileGeneral file, CnfColType type) {
		CnfFieldMapping obj = new CnfFieldMapping();
		obj.setActive("Y");
		obj.setCnfFileGeneral(file);
		obj.setCnfColType(type);
		return obj;
	}
}
