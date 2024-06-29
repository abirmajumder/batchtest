package com.creatorstudio.autocoder.java.util;

import static com.common.util.StringUtil.empty;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.common.util.StringUtil;
import com.creatorstudio.autocoder.java.object.Annotation;
import com.creatorstudio.autocoder.java.object.CommonClass;
import com.creatorstudio.autocoder.java.object.JavaClass;
import com.creatorstudio.autocoder.java.object.JavaInterface;
import com.creatorstudio.autocoder.java.object.JavaMethod;
import com.creatorstudio.autocoder.java.object.Parameter;
import com.creatorstudio.autocoder.java.object.Variable;

public class JavaClassUtil {
	
	public static Properties prop;
	
	public static void addImport( String type, String fullClass) {
		prop.putIfAbsent(type, fullClass);
	}
	
	public static void loadImports() {
		prop = new Properties();
		try (InputStream input = JavaClassUtil.class.getClassLoader().getResourceAsStream("ImportResolver.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	public static Set<String> resolveImportForMethod( JavaMethod m ) {
		Set<String> resolver = new HashSet<>();
		//m.annotations.forEach( JavaClassUtil::resolveImportForAnnotation );
		m.annotations.stream().map( JavaClassUtil::resolveImportForAnnotation ).forEach(  resolver::addAll );
		m.exceptions.forEach( resolver::add );
		m.parameters.forEach( p -> { 
			resolver.addAll(splitForGenericType(p.type));
			//p.annotations.forEach( JavaClassUtil::resolveImportForAnnotation );
			p.annotations.stream().map( JavaClassUtil::resolveImportForAnnotation ).forEach(  resolver::addAll );
		} );
		resolver.addAll(splitForGenericType(m.returning));
		return resolver;
	}
	
	public static List<String> splitForGenericType( String type ) {
		if( type.contains("<") ) {
			return Arrays.asList(StringUtils.substringBefore(type, "<"), StringUtils.substringBetween(type, "<", ">"));
		} else
			return Arrays.asList(type);
	}
	
	public static Set<String> resolveImportForAnnotation( Annotation ann  ) {
		Set<String> resolver = new HashSet<>();
		resolver.add(ann.name);
		resolver.addAll(ann.importHelper);
		return resolver;
	}
	
	public static void resolveImport( CommonClass<?> clz, Supplier<List<String>> parentImports ) {
		final Set<String> resolver = new HashSet<>();
		/*Consumer<Annotation> annoImportResolver = ann -> {
			resolver.add(ann.name);
			resolver.addAll(ann.importHelper);
		};*/
		if( null != parentImports )
			resolver.addAll(parentImports.get());
		//clz.annotations.forEach( annoImportResolver );
		clz.annotations.stream().map( JavaClassUtil::resolveImportForAnnotation ).forEach(  resolver::addAll );
		/*clz.methods.forEach( m -> {
			m.annotations.forEach( annoImportResolver);
			m.exceptions.forEach( resolver::add );
			m.parameters.forEach( p -> { 
				resolver.add(p.name);
				p.annotations.forEach( annoImportResolver );
			} );
			resolver.add(m.returning);
		});*/
		clz.methods.stream().map( JavaClassUtil::resolveImportForMethod ).forEach( resolver::addAll );
		if( clz instanceof JavaClass ) {
			((JavaClass)clz).variables.forEach( v -> {
				resolver.add(v.name); // import prop should have all the models and service classes
				//v.annotations.forEach( annoImportResolver );
				v.annotations.stream().map( JavaClassUtil::resolveImportForAnnotation ).forEach(  resolver::addAll );
			});
		}
		resolver.addAll(clz.generics);
		resolver.addAll(clz.importHelper);
		if( !clz.generics.isEmpty() )
			System.out.println("");
		clz.imports = resolver.stream().map( JavaClassUtil::resolveImport ).filter( s -> null != s).collect(Collectors.toSet());
	}
	
	public static String resolveImport( String type ) {
		return prop.getProperty(type, null);
	}
	
	public static String extending( JavaClass parent ) {
		return ( null == parent || empty(parent.name) ) ? null : ( "extends " + parent.name );
	}
	
	public static String implementing( List<JavaInterface> ifaces) {
		String isfaces = ifaces.stream().map( i -> i.sourceName() ).collect(Collectors.joining(", "));
		return empty(isfaces) ? null : "implements " + isfaces;
	}

	public static String extending(JavaInterface... parents) {
		if( null == parents || parents.length == 0 ) return "";
		return "extends " + Stream.of(parents).map( p -> p.sourceName() ).collect(Collectors.joining(", "));
	}
	
	public static void setget( JavaClass clz, Variable varr ) {
		Parameter param = new Parameter(varr.name, varr.type);
		JavaMethod setter 
			= new JavaMethod("set" + StringUtil.capitalize(varr.name), "public", "void", param);
		setter.instructions.add( k -> StringUtil.mult("\t", k + 1) + "this." + varr.name + " = " + varr.name + ";\n");
		
		JavaMethod getter 
			= new JavaMethod("get" + StringUtil.capitalize(varr.name), "public", varr.type);
		getter.instructions.add( k -> StringUtil.mult("\t", k + 1) + "return this." + varr.name + ";\n" );
		clz.method(setter, getter);
	}
	
	public static String insertImport( String parentClass, String imports ) {
		int ind = parentClass.indexOf("import");
		StringBuilder builder = new StringBuilder(parentClass);
		builder.insert(ind, "\n" + imports + "\n\n");
		System.out.println(builder.toString());
		return builder.toString();
	}
	
	public static String insertImport( String parentClass, Set<String> imports ) {
		int ind = parentClass.indexOf("import");
		StringBuilder builder = new StringBuilder(parentClass);
		
		if( !imports.isEmpty() )
			builder.insert(ind,"\n");
		imports.forEach( imp -> {
			String importCode = resolveImport(imp);
			if( !StringUtil.empty(importCode) && !parentClass.contains(importCode) ) {
				builder.insert(ind+1, "import " + importCode + ";\n");
			}
		});
		System.out.println(builder.toString());
		return builder.toString();
	}
	
	public static String insertMethod( String parentClass, JavaMethod mthd ) {
		/*String imports = resolveImportForMethod(mthd).stream()
								.map( im -> "import " + JavaClassUtil.resolveImport(im) ).collect(Collectors.joining(";\n"));*/
		if( !parentClass.contains(" " + mthd.name +" ") && !parentClass.contains(" " + mthd.name +"(") ) {
			String mSource = mthd.code(1);
			int lastInd = parentClass.lastIndexOf('}');
			StringBuilder builder = new StringBuilder(parentClass);
			builder.insert(lastInd, mSource);
			System.out.println(builder.toString());
			return insertImport(builder.toString(), resolveImportForMethod(mthd));
		}
		return parentClass;
		
	}
	
}
