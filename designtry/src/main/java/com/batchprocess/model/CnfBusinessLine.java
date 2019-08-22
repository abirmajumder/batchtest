
package com.batchprocess.model ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GenerationType ;
import javax.persistence.Table ;
import javax.persistence.GeneratedValue ;
import javax.persistence.OneToMany ;

import java.util.List ;

import com.common.behaviour.ModelBase ;

import javax.persistence.Id ;

import com.batchprocess.model.CnfFileGeneral ;

@Entity
@Table ( name = "cnf_business_line" )
public class CnfBusinessLine implements ModelBase {

	@Column ( name = "business_line" , length = 100 )
	private String businessLine ;
	@OneToMany ( mappedBy="cnfBusinessLine" )
	private List<CnfFileGeneral> cnfFileGeneral ;
	@Column ( name = "active" , length = 1 )
	private String active ;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id ;
	
	public CnfBusinessLine() {
	}
	
	public CnfBusinessLine(Integer id) {
		this.id = id;
	}

	public String getBusinessLine () {
		return this.businessLine ;
	}
	public void setBusinessLine (String businessLine) {
		this.businessLine = businessLine ;
	}
	public List<CnfFileGeneral> getCnfFileGeneral () {
		return this.cnfFileGeneral ;
	}
	public void setCnfFileGeneral (List<CnfFileGeneral> cnfFileGeneral) {
		this.cnfFileGeneral = cnfFileGeneral ;
	}
	public String getActive () {
		return this.active ;
	}
	public void setActive (String active) {
		this.active = active ;
	}
	public Integer getId () {
		return this.id ;
	}
	public void setId (Integer id) {
		this.id = id ;
	}
}