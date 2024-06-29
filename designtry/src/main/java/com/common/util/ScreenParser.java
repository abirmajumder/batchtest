package com.creatorstudio.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.common.object.Node;
import com.common.util.StringUtil;
import com.creatorstudio.autocoder.factory.NgElementFactory;
import com.creatorstudio.autocoder.ngjsp.object.DetachedGrid;
import com.creatorstudio.autocoder.ngjsp.object.Screen;
import com.creatorstudio.autocoder.ngjsp.object.ScreenObject;
import com.creatorstudio.autocoder.sql.DDLScript;
import com.creatorstudio.autocoder.sql.Table;

public class ScreenParser {
	
	private Screen screen;
	private List<String> lines;
	private int seek;
	@SuppressWarnings("unused")
	private String title;
	private DetachedGrid grd;
	private DDLScript script;
	private NgElementFactory elmFactory;
	private Table table;
	private Map<String,Object> entityUrlParams;
	private Map<String,Boolean> entitySearchOnLoadMap;
	
	public ScreenParser( String title, List<String> lines, DDLScript script ) {
		this.screen = new Screen(title);
		this.script = script;
		this.elmFactory = new NgElementFactory(script);
		this.lines = lines;
		seek = 0;
		this.entityUrlParams = new HashMap<>();
		this.entitySearchOnLoadMap = new HashMap<>();
	}
	
	public Screen parse() {
		
		for(; seek < lines.size(); seek++) {
			isSearchOnLoad = false;
			if(lines.get(seek).startsWith("detached")) {
				parseDetached();
			} else if(lines.get(seek).startsWith("editable")) {
				parseEditable();
			}
			//System.out.println(lines.get(seek));
		}
		return screen;
	}
	
	private void parseEditable() {
		String [] parts = lines.get(seek++).split(":");
		String table = parts[1];
		table.substring(1, 11);
		Table acTable = this.script.getTable(table);
		parseSearchParams(acTable, StringUtils.substringAfter(lines.get(seek++), ":"));
	}
	boolean isLoadById = false;
	private void parseSearchParams( Table acTable, String paramStr ) {
		String [] paramParts = paramStr.trim().split(",");
		Map<String,String> prmMap = Stream.of(paramParts).map( s -> s.split("\\s*=\\s*"))
										   .collect(Collectors.toMap( s-> s[0], s->s[1]));
		if(prmMap.containsKey("id")) {
			isLoadById = true;
			this.screen.addScreenObject(
				new ScreenObject(StringUtil.toJavaFieldName(acTable.getName()), "/" + acTable.getRestEntity() + "/${" + prmMap.get("id") + "}" , "objurl") );
			
		} else {
			StringBuilder urlBuilder = new StringBuilder();
			StringBuilder paramBuilder = new StringBuilder();
			boolean isOnloadUrl = true;
			Set<String> srchParams = new HashSet<>();
			for(String param : prmMap.keySet()) {
				String acParam = StringUtil.toJavaFieldName(param);
				urlBuilder.append( ( urlBuilder.length() == 0 ? "" : "And" ) + StringUtil.capitalize(acParam) );
				String paramVal = prmMap.get(param);
				if( paramBuilder.length() > 0 )
					paramBuilder.append("&");
				if( paramVal.startsWith("param") ) {
					paramBuilder.append( acParam + "=${" + paramVal + "}" );
				} else {
					isOnloadUrl = false;
					paramBuilder.append( acParam + "={{" + StringUtil.toJavaFieldName(paramVal) + "}}" );
				}
				srchParams.add(param);
			}
			
			String url = "/search/findBy" + urlBuilder.toString() + "?" + paramBuilder.toString();
			if( isOnloadUrl ) {
				this.screen.addScreenObject(new ScreenObject(acTable.getRestEntity(), url, "url") );
			} else {
				Node span = new Node("span").id(acTable.getRestEntity() + "_url").inner( url );
				this.screen.addToHidden(span);
			}
			this.entityUrlParams.put(table.getRestEntity() + "_tbl", table);
			this.entityUrlParams.put(table.getRestEntity() + "_params", srchParams);
			this.entityUrlParams.put(table.getRestEntity() + "_url", url);
			
		}
		
	}

	private void parseResult( String line ) {
		String [] parts = line.split("\\s*,\\s*");
		Stream
			.of(parts)
			.map( String::trim )
			.forEach( elm -> grd.lookupElement(elmFactory.createNode(table.colFromName(elm)), StringUtil.headerFromColName(elm)));
			//.forEach( grd::lookupElement );
	}
	
	private void parseDetail() {
		while( seek < lines.size() && lines.get(seek).startsWith("\t\t") ) {
			String [] parts = lines.get(seek++).split("\\s*,\\s*"); 
			grd.detailElement(elmFactory.createNode(table.colFromName(parts[0].trim())), StringUtil.headerFromColName(parts[0].trim()));
			//grd.detailElement( parts[0].trim());
		}
	}
	
	private void parseSearch() {
		if( isSearchOnLoad ) {
			parseSearchParams(table,lines.get(seek++));
		} else {
			while( seek < lines.size() && lines.get(seek).startsWith("\t\t") ) {
				String [] parts = lines.get(seek++).split(","); 
				String fieldName = parts[0].replaceAll("\t", "");
				grd.searchElement(fieldName, elmFactory.createNode(table.colFromName(fieldName)));
			}
			
		}
	}
	
	private void parseDetached( ) {
		String line = lines.get(seek++);
		String [] decLineParts = line.split("\\s*:\\s*");
		String [] grpProps = decLineParts[1].split("\\s*,\\s*");
		String tableName = grpProps[0];
		table = script.getTable(tableName);
		isLoadById = false;
		grd = new DetachedGrid(table.getRestEntity(), StringUtils.substringAfterLast(tableName, "_").toLowerCase(), StringUtil.toJavaFieldName(table.getName()), grpProps[1]);
		while( seek < lines.size() ) {
			if( lines.get(seek++).startsWith("\tsearch") ) {
				String [] searchParams = lines.get(seek-1).split(":");
				isSearchOnLoad = searchParams.length > 1 && "onload".equalsIgnoreCase(searchParams[1]);
				entitySearchOnLoadMap.put(tableName, isSearchOnLoad);
				parseSearch();
			} 
			if( lines.get(seek).startsWith("\tresult") ) {
				seek++;
				parseResult(lines.get(seek++));
			}
			if(lines.get(seek).startsWith("\tdetail")) {
				seek++;
				parseDetail();
			}
			break;
		}
		grd.conclude();
		if( isLoadById ) {
			this.screen.addBlock(grd.detailElement());
		} else if( isSearchOnLoad ) {
			this.screen.addBlock(grd.lookupElement());
			this.screen.addBlock(grd.detailElementModal());
		} else {
			this.screen.addBlock(grd.searchElement());
			this.screen.addBlock(grd.lookupElement());
			this.screen.addBlock(grd.detailElementModal());
			this.entityUrlParams.put(table.getRestEntity() + "_tbl", table);
			this.entityUrlParams.put(table.getRestEntity() + "_params", grd.getSearchColParams());
			this.entityUrlParams.put(table.getRestEntity() + "_url", grd.getSearchUrl());
		}
		
		this.screen.addScreenObjects(grd.getScreenObjects());		
		
	}
	boolean isSearchOnLoad = false;
	public String html() {
		return this.screen.html();
	}
	
	public Screen getScreen() {
		return this.screen;
	}
	
	public Map<String,Object> getEntityUrlParams() {
		return entityUrlParams;
	}
	
	public static void main(String[] args) throws Exception {
		//ScreenParser parser = new ScreenParser("Job Edits",Files.readAllLines(Paths.get("C:\\\\Users\\Abirlal\\Desktop\\complex_screen.txt")));
		//parser.parse();
		//System.out.println( parser.html() );
	}
	
}
