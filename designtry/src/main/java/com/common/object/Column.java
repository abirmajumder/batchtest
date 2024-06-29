package com.creatorstudio.autocoder.sql;

import static com.common.util.SQLUtil.restEntity;

import com.common.util.StringUtil;

public class Column {
	
	public 
		String name, 
			type = "input", 
			javaFieldName,
			selectDS,
			referringTable,
			dataType
			;

	public Column(String name) {
		this(name,"varchar");
	}
	
	public Column(String name, String dataType) {
		this.name = name;
		this.dataType = dataType;
		System.out.println("Column > " + name);
		this.javaFieldName = StringUtil.toJavaFieldName(name);
	}

	public void setSelect( String refTable ) {
		this.selectDS = restEntity(refTable);
		this.referringTable = refTable;
		type = "select";
	}

	@Override
	public String toString() {
		return "Column [name=" + name + ", type=" + type + ", javaFieldName="
				+ javaFieldName + ", selectDS=" + selectDS
				+ ", referringTable=" + referringTable + ", dataType="
				+ dataType + "]";
	}

}
