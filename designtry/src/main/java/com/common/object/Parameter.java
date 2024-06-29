package com.creatorstudio.autocoder.java.object;

import static com.creatorstudio.autocoder.java.util.SourceUtil.joiningAnnotations;

import java.util.ArrayList;
import java.util.List;

import com.common.object.ISource;
import com.common.util.StringUtil;

public class Parameter implements ISource {
	
	public String name;
	public String type;
	public List<Annotation> annotations = new ArrayList<>();
	
	public Parameter(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	@Override
	public String code(int indent) {
		return StringUtil.joining(" ", joiningAnnotations(annotations, " "), type, name);
	}
	
	public Parameter annotation(String name, String... attr) {
		annotations.add( new Annotation(name, attr) );
		return this;
	}
	
}
