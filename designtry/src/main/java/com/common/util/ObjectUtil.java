package com.common.util;

import java.util.Objects;
import java.util.stream.Stream;

import com.common.behaviour.ModelBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static Object stringify( Object obj ) throws JsonProcessingException {
		return mapper.writeValueAsString(obj);
	}
	
	public static boolean equalOnId( ModelBase m1, ModelBase m2 ) {
		return notNull(m1,m2) && Objects.nonNull(m1.getId()) && m1.getId().equals(m2.getId());
	}
	
	public static boolean notNull( Object... objects ) {
		return !Stream.of(objects).anyMatch( Objects::isNull );
	}
	
}
