package com.batchprocess.configuration;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.batchprocess.listener.JobCompletionNotificationListener;
import com.batchprocess.processor.MapPersonItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    public FlatFileItemReader<Map<String,String>> reader() {
        LineMapper<Map<String,String>> lineMapper = new LineMapper<Map<String,String>>() {
			
			@Override
			public Map<String,String> mapLine(String line, int lineNumber) throws Exception {
				System.out.println( "Line # : " + lineNumber + " line : " + line );
				String [] parts = line.split(",");
				String [] cols = {"first_name","last_name","company"};
				Map<String,String> lineMap = Stream.of(0,1,2)
						.collect( Collectors.toMap( i -> cols[i] ,  i-> parts[i]) ); 
				return lineMap;
			}
		};
		return new FlatFileItemReaderBuilder<Map<String,String>>()
            .name("personItemReader")
            .resource(new FileSystemResource("\\\\ABIRBUSINESS\\LabReports\\sample-data.csv"))
            .lineMapper(lineMapper)
            .build();
    }

    @Bean
    public JdbcBatchItemWriter<Map<String,Object>> writer(DataSource dataSource) {
    	return
	         new JdbcBatchItemWriterBuilder<Map<String,Object>>()
	        	.columnMapped()	
	            .sql("INSERT INTO person (first_name,last_name,company) VALUES (:first_name,:last_name,:company)")
	            .dataSource(dataSource)
	            .build();
    }
    @Bean("JobBuilder")
    public JobBuilder importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener);
    }

    @Bean("StepOne")
    public Step step1(JdbcBatchItemWriter<Map<String,Object>> writer) {
        return stepBuilderFactory.get("step1")
            .<Map<String,String>, Map<String,Object>> chunk(10)
            .reader(reader())
            .processor(new MapPersonItemProcessor())
            .writer(writer)
            .build();
    }
}