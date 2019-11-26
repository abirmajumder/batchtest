package com.trulygeneric.model;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table ( name = "cnf_data_source" )
public class CnfDataSource {
	@Id @GeneratedValue ( strategy = GenerationType.IDENTITY ) private Integer id;
	@Column ( name = "source_name", length = 100 ) private String sourceName;
	@Column ( name = "source_type", length = 50 ) private String sourceType;
	@Column ( name = "source_params", length = 500 ) private String sourceParams;
	@Column ( name = "job_id" ) private Integer jobId;
	@Column ( name = "active", length = 1 ) private String active;
	
	public String getSourceName (  ) { 
		return this.sourceName;
	}
	
	public void setSourceType ( String sourceType ) { 
		this.sourceType = sourceType;
	}
	
	public void setJobId ( Integer jobId ) { 
		this.jobId = jobId;
	}
	
	public void setActive ( String active ) { 
		this.active = active;
	}
	
	public void setSourceParams ( String sourceParams ) { 
		this.sourceParams = sourceParams;
	}
	
	public Integer getJobId (  ) { 
		return this.jobId;
	}
	
	public void setId ( Integer id ) { 
		this.id = id;
	}
	
	public Integer getId (  ) { 
		return this.id;
	}
	
	public void setSourceName ( String sourceName ) { 
		this.sourceName = sourceName;
	}
	
	public String getSourceType (  ) { 
		return this.sourceType;
	}
	
	public String getSourceParams (  ) { 
		return this.sourceParams;
	}
	
	public String getActive (  ) { 
		return this.active;
	}

}