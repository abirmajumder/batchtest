package com.batchprocess.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.batchprocess.service.ICnfBusinessLineService;
import com.common.util.ObjectUtil;

@Component
public class ModelSupport implements IModelSupport {

	@Autowired
	private ICnfBusinessLineService cnfBusinessLineService ;
	
	@Override
	public IModelSupport businessLines(ModelMap model) throws Exception {
		model.put("lines", ObjectUtil.stringify(
				Optional
					.ofNullable(cnfBusinessLineService.findAll())
					.orElse(new ArrayList<>())
					)
			);
		return this;
	}

	@Override
	public IModelSupport businessLine(ModelMap model, Object line) throws Exception {
		model.put("cnfBusinessLine",line);
		return this;
	}

}
