
package com.batchprocess.model ;

import javax.persistence.Entity ;
import javax.persistence.ManyToOne ;
import javax.persistence.JoinColumn ;
import com.batchprocess.model.CnfFieldMapping ;
import javax.persistence.OneToMany ;
import com.batchprocess.model.CnfQuery ;
import java.util.Set ;
import com.batchprocess.model.CnfBusinessLine ;
import javax.persistence.Column ;
import javax.persistence.GenerationType ;
import javax.persistence.Table ;
import javax.persistence.GeneratedValue ;
import com.common.behaviour.ModelBase ;
import javax.persistence.Id ;

@Entity
@Table ( name = "cnf_file_general" )
public class CnfFileGeneral implements ModelBase {

	@Column ( name = "stage_directory" , length = 500 )
	private String stageDirectory ;
	@Column ( name = "error_directory" , length = 500 )
	private String errorDirectory ;
	@ManyToOne
	@JoinColumn ( name = "cnf_query" )
	private CnfQuery cnfQuery ;
	@OneToMany ( mappedBy="cnfFileGeneral" )
	private Set<CnfFieldMapping> cnfFieldMapping ;
	@Column ( name = "archive_directory" , length = 500 )
	private String archiveDirectory ;
	@Column ( name = "in_vouge" , length = 1 )
	private String inVouge ;
	@JoinColumn ( name = "cnf_business_line" )
	@ManyToOne
	private CnfBusinessLine cnfBusinessLine ;
	@Column ( name = "name" , length = 200 )
	private String name ;
	@Column ( name = "active" , length = 1 )
	private String active ;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id ;
	@Column ( name = "type" , length = 25 )
	private String type ;
	@Column ( name = "batch_size" )
	private int batchSize ;

	public String getStageDirectory () {
		return this.stageDirectory ;
	}
	public void setStageDirectory (String stageDirectory) {
		this.stageDirectory = stageDirectory ;
	}
	public String getErrorDirectory () {
		return this.errorDirectory ;
	}
	public void setErrorDirectory (String errorDirectory) {
		this.errorDirectory = errorDirectory ;
	}
	public CnfQuery getCnfQuery () {
		return this.cnfQuery ;
	}
	public void setCnfQuery (CnfQuery cnfQuery) {
		this.cnfQuery = cnfQuery ;
	}
	public Set<CnfFieldMapping> getCnfFieldMapping () {
		return this.cnfFieldMapping ;
	}
	public void setCnfFieldMapping (Set<CnfFieldMapping> cnfFieldMapping) {
		this.cnfFieldMapping = cnfFieldMapping ;
	}
	public String getArchiveDirectory () {
		return this.archiveDirectory ;
	}
	public void setArchiveDirectory (String archiveDirectory) {
		this.archiveDirectory = archiveDirectory ;
	}
	public String getInVouge () {
		return this.inVouge ;
	}
	public void setInVouge (String inVouge) {
		this.inVouge = inVouge ;
	}
	public CnfBusinessLine getCnfBusinessLine () {
		return this.cnfBusinessLine ;
	}
	public void setCnfBusinessLine (CnfBusinessLine cnfBusinessLine) {
		this.cnfBusinessLine = cnfBusinessLine ;
	}
	public String getName () {
		return this.name ;
	}
	public void setName (String name) {
		this.name = name ;
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
	public int getBatchSize () {
		return this.batchSize ;
	}
	public void setBatchSize (int batchSize) {
		this.batchSize = batchSize ;
	}
}