package com.batchprocess.controller;

import org.springframework.ui.ModelMap;

public interface IModelSupport {
	IModelSupport businessLines( ModelMap model ) throws Exception;
	IModelSupport businessLine( ModelMap model, Object line ) throws Exception;
}
