package com.batchprocess.listener;

import java.util.Map;

import org.springframework.batch.core.SkipListener;
import org.springframework.batch.item.file.FlatFileParseException;


public class MapSkipListener implements SkipListener<Map<String,String>, Map<String,Object>> {

	@Override
	public void onSkipInRead(Throwable t) {
		System.out.println("Reader Skipped :");
		if( t instanceof FlatFileParseException ) {
			System.out.println( ((FlatFileParseException)t).getInput() );
		}
	}

	@Override
	public void onSkipInWrite(Map<String, Object> item, Throwable t) {
		System.out.println("Write Skipped");
	}

	@Override
	public void onSkipInProcess(Map<String, String> item, Throwable t) {
		System.out.println("Process Skipped");
	}

}
