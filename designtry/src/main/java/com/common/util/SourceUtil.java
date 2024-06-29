package com.creatorstudio.autocoder.java.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.creatorstudio.autocoder.java.object.Annotation;
import com.creatorstudio.autocoder.java.object.Parameter;

public class SourceUtil {
	
	static List<String> noClosures = Arrays.asList("length");
	
	public static String joiningAnnotations( List<Annotation> annotations, String delim ) {
		return annotations.stream().map( a -> a.code() ).collect(Collectors.joining(delim));
	}
	
	public static String joiningParams( List<Parameter> annotations, String delim ) {
		return annotations.stream().map( a -> a.code() ).collect(Collectors.joining(delim));
	}
	
	public static String annotationAttr( Entry<String,String> ent ) {
		String enclosure = ent.getValue().contains(".") || noClosures.contains(ent.getKey())? "" : "\"";
		return "".equals(ent.getKey()) ? "\"" + ent.getValue()  + "\"" :
				ent.getKey() + " = " + enclosure + ent.getValue() + enclosure;
	}
	
	public static String exception( List<String> exceptions ) {
		if( null == exceptions || exceptions.size() == 0 ) return "";
		else
			return	" throws " + 
					exceptions.stream().collect(Collectors.joining(", "));
	}
}
