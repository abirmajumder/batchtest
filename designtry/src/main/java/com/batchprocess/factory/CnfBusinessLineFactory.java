
package com.batchprocess.factory ;

import com.batchprocess.model.CnfBusinessLine ;

public class CnfBusinessLineFactory {
	public static CnfBusinessLine create () {
		CnfBusinessLine line = new CnfBusinessLine();
		line.setActive("Y");
		line.setBusinessLine("");
		return line  ;
	}
}