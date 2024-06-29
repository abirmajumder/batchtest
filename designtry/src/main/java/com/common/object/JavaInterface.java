package com.creatorstudio.autocoder.java.object;

import static com.creatorstudio.autocoder.java.util.JavaClassUtil.extending;
import static com.common.util.StringUtil.joining;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.common.object.ISource;
import com.common.util.StringUtil;

public class JavaInterface extends CommonClass<JavaInterface> implements ISource {

	private JavaInterface [] parents;
	
	public JavaInterface(String name, String pacage, JavaInterface... parent) {
		this(name,null,pacage,parent);
	}
	
	public JavaInterface(String name, String access, String pacage, JavaInterface... parent) {
		this.name = name;
		this.access = StringUtil.empty(access) ? "public" : access;
		this.pacage = pacage;
		this.parents = parent;
	}

	@Override
	public String code(int indent) {
		StringBuilder source = commonStructure(
			null == parents || parents.length == 0 ?  null : () -> Stream.of(parents).map( p -> p.name).collect(Collectors.toList()) 
		);
		source.append(joining(" ", access, "interface", sourceName(), extending(parents),"{" ));
		methods.forEach( m-> source.append(m.declaration(1) + ";\n"));
		source.append("}");
		return source.toString();
	}
	
}
