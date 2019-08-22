package com.batchprocess.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {
	
	@Autowired
    private JobLauncher jobLauncher;
	private Job job = null;
	@Autowired 
	private JobBuilder jobBuilder;
	@Autowired @Qualifier("StepOne")  
	private Step step;
	
	@GetMapping("trigger")
	public void perform() throws Exception {
    	JobParameters params = new JobParametersBuilder()
			.addString("JobID", String.valueOf(System.currentTimeMillis()))
			.toJobParameters();
		jobLauncher.run(job(), params);
	}
    
    protected Job job() {
    	return ( null != job ) ? job :  jobBuilder.flow(step).end().build();
    	
    }
	
}
