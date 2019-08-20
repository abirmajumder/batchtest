package com.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidationUtil {
	
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	
	public static Map<String,String> validate( Object obj ) {
		Map<String,String> err = new HashMap<>();
		Set<ConstraintViolation<Object>> violations = validator.validate( obj );
		violations.forEach( v -> {
			StringBuilder field = new StringBuilder();
			v.getPropertyPath().forEach( n -> field.append( "_" + n.getName() ));
			err.put( field.toString(), v.getMessage());
		});
		return err;
	}
}
