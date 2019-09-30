package com.bucket;

import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class Reader {
	
	static String [] credential = {"AKIAYHOBWW2UOL4ECO2R","qUiW84fYHTaL0oMSoieVFh4gUZnPgqJLgRFGl5yd"};
	
	public static InputStream read() throws IOException {
		AWSCredentials credentials = new BasicAWSCredentials(  credential[0], credential[1] );
		AmazonS3 s3client = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(Regions.AP_SOUTH_1)
				  .build();
		String bucketName = "spring-batch-bucket";
		 
		if(s3client.doesBucketExist(bucketName)) {
			S3Object s3object = s3client.getObject(bucketName, "sample-data.csv");
			S3ObjectInputStream inputStream = s3object.getObjectContent();
			//BufferedInputStream is = new BufferedInputStream(inputStream);
			
			//java.io.Reader reader = IOUtils.getBufferedReader(inputStream);
			//IOUtils.readFully(in, buffer, max)
			
			//byte[] data = new byte[200];
			//System.out.println("inputStream.available() : " + inputStream.available());
			//inputStream.read(data );
			//System.out.println( new String(data));
			//System.out.println("inputStream.available() 2 : " + inputStream.available());
			//inputStream.close();
			return inputStream;
		}
		return null;
	}
	
}
