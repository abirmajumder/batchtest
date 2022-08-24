package com.repository;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractRepository<T> {
	
	List<T> data;	
	
	public AbstractRepository( String fileName ) throws Exception {
		this.data = new LinkedList<>();
		File file = new File(fileName);
		String line = "";
	    try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				System.out.println(line);
				this.data.add( map(line) );
			}
		}
	}
	
	public List<T> findAll() {
		return data;
	}
	
	abstract T map(String dataLine);
	
}
