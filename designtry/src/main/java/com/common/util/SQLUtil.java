package com.common.util;

import com.creatorstudio.autocoder.java.object.Variable;
import com.creatorstudio.autocoder.sql.Column;

public class SQLUtil {
	
	public static String joinColumnFromDML( String sql ) {
		int i1 = sql.indexOf('(');
		int i2 = sql.indexOf(')');
		return (i2 > i1 && i1> 1) ? sql.substring(i1+1, i2).trim() : "";
	}
	
	public static String referringTableFromDML( String sql ) {
		int i1 = sql.indexOf("references") + "references".length();
		int i2 = sql.lastIndexOf('(');
		return (i2 > i1 && i1> 1) ? sql.substring(i1+1, i2).trim() : "";
	}
	
	public static String restEntity( String table ) {
		return table + ( table.endsWith("s") ? "es" : "s" );
	}
	
	public static String javaType( String sqlType ) {
		if( sqlType.startsWith("varchar") ) {
			return "String";
		} else if(sqlType.startsWith("int")) {
			return "Integer";
		} else if(StringUtil.in(sqlType, "timestamp","date")) { 
			return "Date";
		} else if(StringUtil.in(sqlType, "float","double","number")) { 
			return "Double";
		} else 
			return "String";
	}
	
	public static Variable variableFromCol( Column col ) {
		Variable varr = null;
		if( "serial".equalsIgnoreCase(col.dataType) ) {
			varr = new Variable(col.javaFieldName,"Integer");
			varr.annotation("Id").annotation("GeneratedValue","strategy", "GenerationType.IDENTITY");
		} else {
			varr = new Variable(col.javaFieldName,javaType(col.dataType));
			String maxlen = StringUtil.substrWithin(col.dataType, "(", ")").trim();
			if( maxlen.length() > 0 )
				varr.annotation("Column", "name",col.name,"length",maxlen);
			else
				varr.annotation("Column", "name",col.name);
		}
		return varr;
	}
	
}