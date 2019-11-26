package com.trulygeneric.model;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table ( name = "cnf_application" )
public class CnfApplication {
	@Id @GeneratedValue ( strategy = GenerationType.IDENTITY ) private Integer id;
	@Column ( name = "application_name", length = 100 ) private String applicationName;
	@Column ( name = "product_name", length = 100 ) private String productName;
	@Column ( name = "active", length = 1 ) private String active;
	
	public String getApplicationName (  ) { 
		return this.applicationName;
	}
	
	public void setActive ( String active ) { 
		this.active = active;
	}
	
	public void setApplicationName ( String applicationName ) { 
		this.applicationName = applicationName;
	}
	
	public void setProductName ( String productName ) { 
		this.productName = productName;
	}
	
	public void setId ( Integer id ) { 
		this.id = id;
	}
	
	public Integer getId (  ) { 
		return this.id;
	}
	
	public String getActive (  ) { 
		return this.active;
	}
	
	public String getProductName (  ) { 
		return this.productName;
	}

}