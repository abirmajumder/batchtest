package com.common.object;

import java.util.HashMap;
import java.util.Map;

import com.common.util.ExceptionUtil;
import com.common.util.functional.Try;
import com.common.util.functional.TryMapFunction;

public class Work {
	private Object item;
	private Map<String,String> errors;
	private boolean success;
	
	public static <T> Work on( T obj, TryMapFunction<T,T> action ) {
		return
			Try.ofFailable( () -> obj )
			   .map( action )
			   .map( Work::new )
			   .recover( ExceptionUtil::handleError );
	}
	
	public static <T> Work on( T obj, Try<T> action ) {
		return
			action
			   .map( Work::new )
			   .recover( ExceptionUtil::handleError );
	}
	
	public Work() {
		this.errors = new HashMap<>();
		this.success = true;
	}

	public Work( Object item ) {
		this();
		this.item = item;
	}
	
	public Work( Map<String, String> errors ) {
		this.errors = errors;
		this.success = errors.isEmpty();
	}
	
	public Work(Object item, Map<String, String> errors) {
		this(errors);
		this.item = item;
	}

	public Map<String, String> getErrors() {
		return errors;
	}
	
	public void addError( String key, String msg ) {
		this.errors.put(key, msg);
	}
	
	public Object getItem() {
		return item;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
}
