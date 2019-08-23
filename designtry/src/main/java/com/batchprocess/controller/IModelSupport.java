package com.batchprocess.controller;

import org.springframework.ui.ModelMap;

public interface IModelSupport {
	IModelSupport businessLines( ModelMap model ) throws Exception;
	IModelSupport businessLine( ModelMap model, Object line ) throws Exception;
	IModelSupport newfile(Integer lineId, ModelMap model) throws Exception;
	IModelSupport newMapping(Integer id, ModelMap model) throws Exception;
}
