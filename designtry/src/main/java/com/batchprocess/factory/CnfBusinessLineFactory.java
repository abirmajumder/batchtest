
package com.batchprocess.factory ;

import com.batchprocess.model.CnfBusinessLine ;

public class CnfBusinessLineFactory {
	public static CnfBusinessLine create () {
		return new CnfBusinessLine() ;
	}
}