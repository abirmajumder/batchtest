package com.creatorstudio.autocoder.java.object;

import static com.creatorstudio.autocoder.java.util.SourceUtil.joiningAnnotations;
import static com.common.util.StringUtil.mult;
import static com.common.util.StringUtil.joining;

public class Variable extends Parameter {
	public String access;
	
	public Variable(String access, String name, String type) {
		super(name,type);
		this.access = access;
	}
	
	public Variable(String name, String type) {
		super(name,type);
		this.access = "private";
	}
	
	@Override
	public String code(int indent) {
		return mult("\t", indent) + joining(" ", joiningAnnotations(annotations, " "), access, type, name) + ";\n";
	}
}
