package com.common.behaviour;

import static com.common.util.SQLUtil.referringTableFromDML;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

import com.common.util.FileUtil;
import com.common.util.SQLUtil;
import com.common.util.StringUtil;
import com.creatorstudio.autocoder.java.object.JavaClass;
import com.creatorstudio.autocoder.java.object.JavaInterface;
import com.creatorstudio.autocoder.java.object.Variable;
import com.creatorstudio.autocoder.java.util.JavaClassUtil;

public class DDLScript {
	
	private List<Table> tables;
	private Map<String,Table> tableMap;
	private String appName;
	private String path;
	
	 
	private DDLScript() {
		this.tables = new ArrayList<>();
		this.tableMap = new HashMap<>();
		this.appName = StringUtil.empty(appName) ? "" : appName;
	}
	
	public DDLScript(String appName) {
		this();
		this.appName = appName;
	}

	public List<Table> tables() {
		return tables;
	}
	
	public static List<String> tableNames( String script ) {
		return
				Stream.of(script.split("\n"))
					.filter( s -> s.startsWith("create"))
					.map( s -> s.trim().split("\\s+"))
					.map( s -> s[2])
					.collect(Collectors.toList());
	}
	
	public static DDLScript createFromLines( List<String> lines, String app, String projectPath) {
		DDLScript obj = new DDLScript(app);
		obj.path = projectPath;
		Table table = null;
		for( String line : lines ) {
			String [] parts = line.trim().split("\\s+");
			if( parts[0].equalsIgnoreCase("create") ) {
				table = new Table(parts[2]);
				obj.tables.add(table);
				obj.tableMap.put(table.getName(), table);
			} /*else if(line.contains(" references ")) {
				Optional.ofNullable(table.colMap.get( joinColumnFromDML(line) ))
				Optional.ofNullable(table.colMap.get(parts[0].trim()))
						.ifPresent( cl -> cl.setSelect(referringTableFromDML(line)));
			} */ else {
				if( !");".equals(parts[0]) && !parts[0].isEmpty() ) { 
					table.col(parts[0],parts[1]);
					if(line.contains(" references ")) {
						table.colMap.get(parts[0]).setSelect(referringTableFromDML(line));
					}
				}
			}
		}
		return obj;
	}
	
	public static DDLScript createFromString( String script, String app, String projectPath) {
		return createFromLines(Stream.of(script.split("\n")).collect(Collectors.toList()), app, projectPath);
	}
	
	public static DDLScript createFromFile( String file, String app, String projectPath ) throws IOException {
		return createFromLines(FileUtil.readLines(new File(file)), app, projectPath);
	}
	
	public static DDLScript createFromFile( String file, String projectPath ) throws IOException {
		return createFromFile(file, "", projectPath);
	}
	
	public void generateRepository() {
		tables.forEach( tb -> {
			JavaInterface repoFace = new JavaInterface("JpaRepository", "org.springframework.data.jpa.repository");
			
			String entityName = StringUtil.capitalize(StringUtil.toJavaFieldName(tb.getName()));
			repoFace.generics.add(entityName);
			repoFace.generics.add("Integer");
			Supplier<String> app = () -> StringUtil.empty(this.appName) ? "" : "." + this.appName;
			String modelPkg = "com" + app.get() +  ".model";
			String repoPkg = "com" + app.get() +  ".repository";
			
			JavaClass entityClz = new JavaClass( entityName , modelPkg)
										.annotation("Entity").annotation("Table", "name", tb.getName());
								
			JavaInterface repoClz = new JavaInterface( entityName + "Repository", repoPkg, repoFace)
									.annotation("RepositoryRestResource", "collectionResourceRel",tb.getRestEntity(),
											"path", tb.getRestEntity());
			repoClz.importHelper.add(entityName);
			JavaClassUtil.prop.setProperty(entityName, modelPkg + "." + entityName);
			JavaClassUtil.prop.setProperty(entityName + "Repository", repoPkg );
			
			tb.cols().forEach( col ->  { 
				Variable varr = SQLUtil.variableFromCol(col);
				entityClz.variables.add( varr );
				JavaClassUtil.setget(entityClz, varr);
			});
			saveModel(entityClz);
			saveRepo(repoClz);
			System.out.println(entityClz.code());
			System.out.println(repoClz.code());
		});
		
	}
	
	public void addTable( Table table ) {
		this.tables.add( table );
	}
	
	public Table getTable(String name) {
		return tableMap.get(name);
	}
	
	public void saveModel(JavaClass clz) {
		try {
			FileUtils.writeStringToFile(new File(path + "\\model\\" + clz.name + ".java"), clz.code(), Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveRepo( JavaInterface clz ) {
		try {
			FileUtils.writeStringToFile(new File(path + "\\repository\\" + clz.name + ".java"), clz.code(), Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main( String [] arg) throws IOException {
		String path = "C:\\\\Users\\Abirlal\\Documents\\project\\workspace\\autocode\\CreatorStudio\\src\\main\\java\\com\\creatorstudio\\";
		String scriptFile = "C:\\\\Abirlal Majumder\\project\\ConfigFiles\\creation_studio_tables_notepad.sql";
		DDLScript ddl = DDLScript.createFromFile(scriptFile, "creatorstudio", path);
		ddl.generateRepository();
	}
}
