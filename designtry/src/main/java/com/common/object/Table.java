package com.creatorstudio.autocoder.sql;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.common.util.SQLUtil;

public class Table {
	
	private String name,restEntity;
	private List<Column> cols = new ArrayList<>();
	public Map<String,Column> colMap = new LinkedHashMap<>();
	
	public Table(String name) {
		this.name = name;
		this.restEntity = SQLUtil.restEntity(name);
	}
	
	public Table col( String name, String dataType ) {
		Column col = new Column(name, dataType);
		colMap.put(name, col );
		cols.add( col );
		return this;
	}
	
	public List<Column> cols() {
		return cols;
	}
	
	public Column colFromName( String colName ) {
		return colMap.get(colName);
	}

	@Override
	public String toString() {
		return "TABLE : " + name + "\n" + cols.stream().map( c -> c.toString()).collect(Collectors.joining("\n"));
	}
	
	public String getName() {
		return name;
	}
	
	public String getRestEntity() {
		return restEntity;
	}
	
	@Override
	public boolean equals(Object obj) {
		if( null != obj && obj instanceof Table ) {
			return null != name && name.equals( ((Table)obj).getName() );
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return null != name ? name.hashCode() : super.hashCode();
	}
	
}
