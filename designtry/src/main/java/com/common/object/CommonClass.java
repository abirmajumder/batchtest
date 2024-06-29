package com.creatorstudio.autocoder.java.object;

import static com.common.util.StringUtil.empty;
import static com.creatorstudio.autocoder.java.util.JavaClassUtil.resolveImport;
import static com.creatorstudio.autocoder.java.util.SourceUtil.joiningAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonClass<E extends CommonClass<E>> {
	
	public List<Annotation> annotations = new ArrayList<>();
	public Set<String> imports = new HashSet<>();;
	public String name,access = "public",pacage;
	public Set<String> importHelper = new HashSet<>();
	public List<JavaInterface> interfaces = new ArrayList<>();
	public Set<JavaMethod> methods = new HashSet<>();
	public Set<String> generics = new LinkedHashSet<>();
	
	public StringBuilder commonStructure(Supplier<List<String>> parentImports) {
		StringBuilder source = new StringBuilder( empty(pacage) ? "" : "package " + pacage +";\n\n" );
		resolveImport(this, parentImports);
		imports.forEach( im -> source.append("import " + im +";\n") );
		Optional.ofNullable( joiningAnnotations(annotations, "\n") ).ifPresent( ann -> source.append("\n" + ann + "\n") );
		return source;
	}
	
	@SuppressWarnings("unchecked")
	public E annotation( String name, String... attr ) {
		annotations.add( new Annotation(name, attr));
		return (E) this;
	}
	
	@SuppressWarnings("unchecked")
	public E behaviour( JavaInterface iface ) {
		interfaces.add( iface );
		return (E) this;
	}
	
	@SuppressWarnings("unchecked")
	public E method( JavaMethod... mtd ) {
		Stream.of(mtd).forEach(methods::add);
		return (E) this;
	}
	
	public String sourceName() {
		if( this.generics.isEmpty() ) {
			return this.name;
		} else {
			return this.name + "<" + this.generics.stream().collect(Collectors.joining(",")) + ">";
		}
	}
}
