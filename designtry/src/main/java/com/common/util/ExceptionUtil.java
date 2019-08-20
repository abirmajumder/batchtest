package com.common.util;

import static java.util.stream.Collectors.toMap;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import com.common.object.Work;

public class ExceptionUtil {
	
	public static Work handleConstraints( ConstraintViolationException ce ) {
		return new Work(
			ce  .getConstraintViolations().stream()
				.collect( toMap( ex -> ex.getPropertyPath().toString(), ex -> ex.getMessage()) )
			);
	}
	
	public static Work handleError( Throwable e ) {
		if( e instanceof ConstraintViolationException ) {
			return handleConstraints( (ConstraintViolationException)e );
		}
		Map<String,String> errs = new HashMap<>();
		errs.put("msg", e.getMessage());
		return new Work(errs);
	}
	
}
