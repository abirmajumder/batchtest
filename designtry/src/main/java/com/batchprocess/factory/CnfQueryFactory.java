package com.batchprocess.factory;

import com.batchprocess.model.CnfQuery;

public class CnfQueryFactory {
	public static CnfQuery create() {
		CnfQuery obj = new CnfQuery();
		obj.setActive("Y");
		return obj;
	}
}
