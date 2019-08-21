package com.batchprocess;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.batchprocess.factory.CnfBusinessLineFactory;
import com.batchprocess.model.CnfBusinessLine;
import com.batchprocess.service.ICnfBusinessLineService;
import com.common.object.Work;
import com.common.util.ObjectUtil;

public class CnfBusinessLineController {
	@Autowired
	private ICnfBusinessLineService cnfBusinessLineService ;

	@GetMapping( "business_line" )
	public String screen (ModelMap model) throws Exception {
		CnfBusinessLine obj = CnfBusinessLineFactory.create();
		model.put("cnfBusinessLine", ObjectUtil.stringify( obj )) ;
		model.put("lines", ObjectUtil.stringify(
			Optional
				.ofNullable(cnfBusinessLineService.findAll())
				.orElse(new ArrayList<>())
				)
		);
		return "business_line" ;
	}
	@PostMapping( "persist" )
	public @ResponseBody Work persist (@RequestBody CnfBusinessLine cnfBusinessLine) throws Exception {
		return Work.on( cnfBusinessLine , cnfBusinessLineService::persist ) ;
	}
}
