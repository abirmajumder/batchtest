
package com.batchprocess.model ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GenerationType ;
import javax.persistence.Table ;
import javax.persistence.GeneratedValue ;
import com.batchprocess.model.CnfFieldMapping ;
import javax.persistence.OneToMany ;
import java.util.Set ;
import com.common.behaviour.ModelBase ;
import javax.persistence.Id ;

@Entity
@Table ( name = "cnf_col_type" )
public class CnfColType implements ModelBase {

	@OneToMany ( mappedBy="cnfColType" )
	private Set<CnfFieldMapping> cnfFieldMapping ;
	@Column ( name = "active" , length = 1 )
	private String active ;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id ;
	@Column ( name = "type" , length = 50 )
	private String type ;

	public Set<CnfFieldMapping> getCnfFieldMapping () {
		return this.cnfFieldMapping ;
	}
	public void setCnfFieldMapping (Set<CnfFieldMapping> cnfFieldMapping) {
		this.cnfFieldMapping = cnfFieldMapping ;
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
	public String getType () {
		return this.type ;
	}
	public void setType (String type) {
		this.type = type ;
	}
}