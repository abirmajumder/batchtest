package com.creatorstudio.autocoder.java.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.common.object.ISource;
import com.common.util.StringUtil;

import static com.creatorstudio.autocoder.java.util.JavaClassUtil.*;
import static com.common.util.StringUtil.*;

public class JavaClass extends CommonClass<JavaClass> implements ISource {
	
	public boolean isFinal, isStatic;
	public List<Variable> variables = new ArrayList<>();
	public JavaClass parent; 
	
	public JavaClass(String name, String pacage) {
		this(name,"public",pacage);
	}
	
	public JavaClass(String name, String access, String pacage) {
		this.name = name;
		this.access = StringUtil.empty(access) ? "public" : access;
		this.pacage = pacage;
	}
	
	public JavaClass variable( String name, String type ) {
		return variable("private", name, type);
	}
	
	public JavaClass variable( String access, String name, String type ) {
		variables.add( new Variable( access, name, type ) );
		return this;
	}
	
	@Override
	public String code() {
		return code(1);
	}
	 
	@Override
	public String code(int indent) {
		StringBuilder source = commonStructure(null == parent ?  null : () -> Arrays.asList(parent.name));
		
		source.append(joining(" ", access, "class", sourceName(), extending(parent), implementing(interfaces),"{\n" ));
		variables.forEach(v -> source.append(v.code(indent)));
		methods.forEach( m-> source.append(m.code(indent)));
		source.append("\n}");
		
		return source.toString();
	}
	
}
