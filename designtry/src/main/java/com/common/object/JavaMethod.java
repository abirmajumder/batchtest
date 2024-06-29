package com.creatorstudio.autocoder.java.object;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.creatorstudio.autocoder.java.util.SourceUtil.*;
import static com.common.util.StringUtil.*;

import com.common.object.ISource;
import com.creatorstudio.autocoder.java.util.SourceUtil;

public class JavaMethod implements ISource {
	
	public List<Annotation> annotations;
	public String name,access,returning;
	public List<Parameter> parameters;
	public List<ISource> instructions;
	public List<String>	exceptions = new ArrayList<>();
	private boolean isAbstract;
	
	public JavaMethod(String name, String access, String returning,
			Parameter... parameters) {
		this.name = name;
		this.access = access;
		this.returning = returning;
		this.parameters = Stream.of(parameters).collect(Collectors.toList());
		this.annotations = new ArrayList<>();
		this.instructions = new ArrayList<>();
	}
	
	public JavaMethod setAbstract( boolean isAbstract ) {
		this.isAbstract = isAbstract;
		return this;
	}
	
	public String declaration( int indent ) {
		return 
			joining("\n", mult("\t",indent) + joiningAnnotations(annotations, "\n"),
				mult("\t",indent) + joining(" ", access, returning, name, "( " 
						+ joiningParams(parameters, ", ") + " )", SourceUtil.exception(exceptions)));
	}

	@Override
	public String code(int indent) {
		StringBuilder source = new StringBuilder();
		source.append(declaration(indent));
		if( isAbstract )
			source.append(";\n");
		else {
			source.append(" { \n");
			this.instructions.forEach( i -> source.append(i.code(indent)));
			if( instructions.isEmpty()
					&&	null != returning && !returning.isEmpty() 
					&& !"void".equals(returning.trim()) )
				source.append(mult("\t",indent + 1) + "return null;\n");
			source.append(mult("\t",indent) + "}\n");
		}
		return source.toString();
	}
	
	public JavaMethod annotation( Annotation anno ) {
		annotations.add(anno);
		return this;
	}
	
	public JavaMethod exception( String exception ) {
		this.exceptions.add(exception);
		return this;
	}
	
	public JavaMethod param( Parameter param ) {
		this.parameters.add( param );
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {
		return null != obj && obj instanceof JavaMethod && null != name && name.equals( ((JavaMethod)obj).name );
	}
	
	@Override
	public int hashCode() {
		return null != name ? name.hashCode() : -1;
	}
}
