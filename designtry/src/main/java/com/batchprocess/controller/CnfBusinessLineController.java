
package com.batchprocess.controller ;

import org.springframework.web.bind.annotation.PostMapping ;

import com.common.object.Work ;

import org.springframework.beans.factory.annotation.Autowired ;

import com.batchprocess.model.CnfBusinessLine ;

import org.springframework.stereotype.Controller ;
import org.springframework.web.bind.annotation.ResponseBody ;
import org.springframework.ui.ModelMap ;

import com.common.util.ObjectUtil ;

import org.springframework.web.bind.annotation.RequestBody ;
import org.springframework.web.bind.annotation.GetMapping ;

import com.batchprocess.service.ICnfBusinessLineService ;
import com.batchprocess.factory.CnfBusinessLineFactory ;

@Controller
public class CnfBusinessLineController {

	@Autowired private ICnfBusinessLineService cnfBusinessLineService ;
	@Autowired private IModelSupport modelSupport;

	@GetMapping( "business_line" )
	public String screen (ModelMap model) throws Exception {
		modelSupport
			.businessLines(model)
			.businessLine(model, ObjectUtil.stringify( CnfBusinessLineFactory.create() )) ;
		return "business_line" ;
	}
	
	@PostMapping( "persistBL" )
	public @ResponseBody Work persist (@RequestBody CnfBusinessLine cnfBusinessLine) throws Exception {
		return Work.on( cnfBusinessLine , cnfBusinessLineService::save ) ;
	}
	
	@PostMapping( "removeBL" )
	public @ResponseBody Work remove (@RequestBody CnfBusinessLine cnfBusinessLine) throws Exception {
		return Work.on( cnfBusinessLine , cnfBusinessLineService::remove ) ;
	}
	
	@PostMapping( "configureBL" )
	public String configure (@RequestBody CnfBusinessLine cnfBusinessLine) throws Exception {
		return "business_Configure" ;
	}
}