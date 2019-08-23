package com.batchprocess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.batchprocess.model.CnfFieldMapping;
import com.batchprocess.model.CnfFileGeneral;
import com.batchprocess.service.ICnfFieldMappingService;
import com.common.object.Work;

@Controller
public class CnfFieldMappingController {
	@Autowired private ICnfFieldMappingService mappingService;
	@Autowired private IModelSupport modelSupport;
	
	@GetMapping("getmappings")
	public @ResponseBody List<CnfFieldMapping> getMappings( @RequestParam("fileId") Integer id ) {
		return mappingService.findByCnfFileGeneral( new CnfFileGeneral(id) );
	}

	@GetMapping("newmapping")
	public String newfile( @RequestParam("id") Integer id, ModelMap model ) throws Exception {
		modelSupport.newMapping(id, model);
		return "config_mapping";
	}
	
	@PostMapping("persistMapping")
	public @ResponseBody Work persistMapping( @RequestBody CnfFieldMapping obj ) {
		return Work.on(obj, mappingService::save );
	}
}
