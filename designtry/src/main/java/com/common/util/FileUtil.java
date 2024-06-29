package com.common.util;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	
	public static void copyDirectory( String from, String to) throws IOException {
		FileUtils.copyDirectory(new File(from), new File(to));
	}

    public static String persist( String data, String path ) {
        String status = "SUCCESS";
        try ( OutputStream os =  new FileOutputStream(path) ){
            os.write( data.getBytes());
        } catch( Exception e ) {  e.printStackTrace(); status = "FAILURE : " + e.getMessage(); }
        return status;
    }
    
    public static String fileName( File file ) {
		return file.getName().substring(0, file.getName().length() - 3);
	}
    
    public static List<String> readLines (File file) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(file));
    	List<String> lines = reader.lines().collect(Collectors.toList());
    	reader.close();
    	return lines;
    }
    
    public static Stream<String> linesStream (File file) throws IOException {
    	BufferedReader reader = new BufferedReader(new FileReader(file));
    	Stream<String> lines = reader.lines();
    	reader.close();
    	return lines;
    }
    
    public static void main(String [] arg ) {
		try {
			copyDirectory("C:\\\\Users\\Abirlal\\Documents\\Project\\workspace\\autocode\\demo", 
					"C:\\\\Abirlal Majumder\\project\\AutoCode\\temp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}