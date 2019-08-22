package com.batchprocess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.batchprocess.model.CnfBusinessLine;
import com.batchprocess.service.ICnfBusinessLineService;

@Controller
public class ServiceController {
	
	@Autowired ICnfBusinessLineService blService;
	@Autowired ApplicationContext ctx;

	@GetMapping("info")
	public String info( ModelMap model ) {
		System.out.println( ctx.getBean("transactionManager") );
		//Stream.of( ctx.getBeanDefinitionNames() ).forEach( System.out::println );;
		//CnfBusinessLine bl = blService.findByBusinessLine("Disability");
		//model.put("bl", bl);
		return "info";
	}
	
}
