package com.batchprocess.processor;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;

public class MapPersonItemProcessor implements ItemProcessor<Map<String,String>,Map<String,Object>> {

	@Override
	public Map<String, Object> process(Map<String, String> item)
			throws Exception {
		Map<String, Object> result = item.keySet()
				.stream()
				.collect(Collectors.toMap( c -> c , c -> item.get(c) )); 
		return result;
			
	}

}
