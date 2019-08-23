package com.batchprocess.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.batchprocess.factory.CnfFieldMappingFactory;
import com.batchprocess.factory.CnfFileGeneralFactory;
import com.batchprocess.model.CnfBusinessLine;
import com.batchprocess.model.CnfColType;
import com.batchprocess.model.CnfFieldMapping;
import com.batchprocess.model.CnfFileGeneral;
import com.batchprocess.service.ICnfBusinessLineService;
import com.batchprocess.service.ICnfColTypeService;
import com.batchprocess.service.ICnfFileGeneralService;
import com.common.util.ObjectUtil;

@Component
public class ModelSupport implements IModelSupport {

	@Autowired private ICnfBusinessLineService cnfBusinessLineService ;
	@Autowired private ICnfColTypeService typeService;
	@Autowired private ICnfFileGeneralService fileService;
	
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

	@Override
	public IModelSupport newfile(Integer lineId, ModelMap model) throws Exception {
		CnfBusinessLine line = cnfBusinessLineService.get(lineId);
		CnfFileGeneral file = CnfFileGeneralFactory.create(line );
		model.put("file", ObjectUtil.stringify(file) );
		return this;
	}

	@Override
	public IModelSupport newMapping(Integer id, ModelMap model) throws Exception {
		CnfFileGeneral file = fileService.get(id);
		List<CnfColType> types = typeService.findAll();
		CnfFieldMapping mapping = CnfFieldMappingFactory.create(file, types.get(0));
		List<CnfFieldMapping> mappings = new ArrayList<>();
		mappings.add(mapping);
		model.put("file", ObjectUtil.stringify(file) );
		model.put("types", ObjectUtil.stringify(types) );
		model.put("mapping", ObjectUtil.stringify(mapping) );
		model.put("mappings", ObjectUtil.stringify(mappings) );
		return this;
	}

}
