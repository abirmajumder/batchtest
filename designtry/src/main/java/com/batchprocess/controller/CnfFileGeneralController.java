package com.batchprocess.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.batchprocess.model.CnfBusinessLine;
import com.batchprocess.model.CnfFileGeneral;
import com.batchprocess.service.ICnfFileGeneralService;

@RestController
public class CnfFileGeneralController {
	
	@Autowired private ICnfFileGeneralService fileService;
	
	@GetMapping("getfiles")
	public @ResponseBody List<CnfFileGeneral> getFiles( @RequestParam("fileId") Integer id ) {
		return fileService.findByCnfBusinessLine( new CnfBusinessLine(id));
	}

}
