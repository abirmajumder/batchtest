package com.batchprocess.configuration;

import java.util.Optional;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;

public class SkippingPolicy implements SkipPolicy {

	@Override
	public boolean shouldSkip(Throwable t, int skipCount)
			throws SkipLimitExceededException {
		Optional.of(t)
				.filter( e -> e instanceof FlatFileParseException )
				.map( e -> (FlatFileParseException)e)
				.ifPresent( e -> System.out.println(e.getInput()) );
		
		return true;
	}

}
