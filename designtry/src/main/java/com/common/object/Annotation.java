package com.creatorstudio.autocoder.java.object;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.common.object.ISource;
import com.common.util.StringUtil;
import com.creatorstudio.autocoder.java.util.SourceUtil;

public class Annotation implements ISource {
	
	public String name;
	public Map<String,String> attributes = new HashMap<>();
	public Set<String> importHelper = new HashSet<>();

	public Annotation() {
	}

	public Annotation(String name, String... attributes) {
		this.name = name;
		if( null != attributes ) {
			if( attributes.length == 1 )
				this.add("", attributes[0]);
			else
				IntStream.iterate(1, i -> i+2)
					 .limit(attributes.length/2)
					 .forEach( i -> this.add( attributes[i-1],attributes[i]));
		}
	}
	
	private void add( String key, String val ) {
		if( val.contains(".") ) {
			this.importHelper.add( val.substring(0, val.indexOf('.')) );
		}
		this.attributes.put(key, val);
	}

	@Override
	public String code(int indent) {
		return
				StringUtil.mult("\t", indent)
				+ "@"
				+ name
				+ ( attributes.isEmpty()?"": " ( " +
						attributes.entrySet()
						.stream()
						.map( SourceUtil::annotationAttr  )
						.collect(Collectors.joining(", ")) + " )" );
	}

}
