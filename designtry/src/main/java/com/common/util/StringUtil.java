package com.common.util;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringUtil {

    public static boolean empty ( String str) {
        return null == str || str.isEmpty();
    }
    public static String safe( String s) {
        return empty(s) ? "" : s;
    }
    public static String mult( String s, int times) {
        return
            ( 0 == times || empty(s) ) ? "" :
                IntStream.range(0, times)
                     .mapToObj( i -> s)
                     .collect( Collectors.joining() );
    }
    public static String joining ( String delimiter, String... s) {
        return
            IntStream.range(0, s.length)
                     .mapToObj( i -> safe( s[i]) )
                     .filter( str -> str.length() > 0 )
                     .collect(Collectors.joining(delimiter));
    }

    public static String smallFirstChar( String s ) {
        return ( "" + s.charAt(0) ).toLowerCase() + s.substring(1);        
    }

    public static String capitalize( String s ) {
        return ( "" + s.charAt(0) ).toUpperCase() + s.substring(1);        
    }
    
    public static String id2Label( String id ) {
        return (id.charAt(0) + "").toUpperCase() +
            IntStream
                .range(1, id.length())
                .mapToObj( i -> (id.charAt(i) >= 'A' && id.charAt(i) <= 'Z') ? (" " + id.charAt(i)) : (id.charAt(i)+"") )
                .collect(Collectors.joining());
    }
    
    public static String toJavaFieldName( String col ) {
    	return (col.charAt(0) + "").toLowerCase() +
			    	IntStream.range(1, col.length())
			    		.filter( i -> col.charAt(i) != '_' && col.charAt(i) != ' ' )
			    		.mapToObj( i -> 
			    					( col.charAt(i - 1) == '_' ) ? (col.charAt(i) + "").toUpperCase() : (col.charAt(i)+"")
			    				)
			    		.collect(Collectors.joining());
    }
    
    public static String headerFromColName( String col ) {
    	return (col.charAt(0) + "").toUpperCase() +
			    	IntStream.range(1, col.length())
			    		.filter( i -> col.charAt(i) != '_' )
			    		.mapToObj( i -> 
			    					( col.charAt(i - 1) == '_' ) ? (" " + col.charAt(i)).toUpperCase() : (col.charAt(i)+"")
			    				)
			    		.collect(Collectors.joining());
    }
    
    public static String toSqlColumnName( String id ) {
        return (id.charAt(0) + "").toLowerCase() +
            IntStream
                .range(1, id.length())
                .mapToObj( i -> (id.charAt(i) >= 'A' && id.charAt(i) <= 'Z') ? ("_" + id.charAt(i)).toLowerCase() : (id.charAt(i)+"") )
                .collect(Collectors.joining());
    }
    
    public static String substrtill( String str, String delim ) {
		int index = str.indexOf( delim );
		return index <= 0 ? str : str.substring(0, index);
	}

    public static String substrWithin( String str, String sDelim, String eDelim ) {
		int sIndex = str.indexOf( sDelim );
		int eIndex = str.indexOf( eDelim );
		return ( sIndex >= 0 && eIndex > sIndex ) ? str.substring(sIndex + 1, eIndex) : "";
	}
    
    public static boolean in(String str, String... options) {
		return Stream.of(options).anyMatch( op -> op.equals(str));
	}
}