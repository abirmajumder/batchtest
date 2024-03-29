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

import com.batchprocess.model.CnfBusinessLine;
import com.batchprocess.model.CnfFileGeneral;
import com.batchprocess.service.ICnfFileGeneralService;
import com.common.object.Work;

@Controller
public class CnfFileGeneralController {
	
	@Autowired private ICnfFileGeneralService fileService;
	@Autowired private IModelSupport modelSupport;
	
	@GetMapping("getfiles")
	public @ResponseBody List<CnfFileGeneral> getFiles( @RequestParam("fileId") Integer id ) {
		return fileService.findByCnfBusinessLine( new CnfBusinessLine(id));
	}

	@GetMapping("newfile")
	public String newfile( @RequestParam("id") Integer id, ModelMap model ) throws Exception {
		modelSupport.newfile(id, model);
		return "config_file";
	}
	
	@PostMapping("persistFile")
	public @ResponseBody Work persistFile( @RequestBody CnfFileGeneral obj ) {
		return Work.on(obj, fileService::persist );
	}

}
