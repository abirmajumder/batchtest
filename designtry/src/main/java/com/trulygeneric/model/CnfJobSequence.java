package com.trulygeneric.model;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table ( name = "cnf_job_sequence" )
public class CnfJobSequence {
	@Id @GeneratedValue ( strategy = GenerationType.IDENTITY ) private Integer id;
	@Column ( name = "step_name", length = 100 ) private String stepName;
	@Column ( name = "step_params", length = 500 ) private String stepParams;
	@Column ( name = "sequence" ) private Integer sequence;
	@Column ( name = "job_id" ) private Integer jobId;
	@Column ( name = "active", length = 1 ) private String active;
	
	public void setSequence ( Integer sequence ) { 
		this.sequence = sequence;
	}
	
	public String getStepName (  ) { 
		return this.stepName;
	}
	
	public void setStepParams ( String stepParams ) { 
		this.stepParams = stepParams;
	}
	
	public void setJobId ( Integer jobId ) { 
		this.jobId = jobId;
	}
	
	public void setActive ( String active ) { 
		this.active = active;
	}
	
	public Integer getJobId (  ) { 
		return this.jobId;
	}
	
	public void setId ( Integer id ) { 
		this.id = id;
	}
	
	public String getStepParams (  ) { 
		return this.stepParams;
	}
	
	public Integer getId (  ) { 
		return this.id;
	}
	
	public String getActive (  ) { 
		return this.active;
	}
	
	public void setStepName ( String stepName ) { 
		this.stepName = stepName;
	}
	
	public Integer getSequence (  ) { 
		return this.sequence;
	}

}