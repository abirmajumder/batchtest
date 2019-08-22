
package com.batchprocess.model ;

import com.batchprocess.model.CnfColType ;
import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GenerationType ;
import javax.persistence.ManyToOne ;
import javax.persistence.Table ;
import javax.persistence.GeneratedValue ;
import javax.persistence.JoinColumn ;
import com.common.behaviour.ModelBase ;
import javax.persistence.Id ;
import com.batchprocess.model.CnfFileGeneral ;

@Entity
@Table ( name = "cnf_field_mapping" )
public class CnfFieldMapping implements ModelBase {

	@ManyToOne
	@JoinColumn ( name = "cnf_col_type" )
	private CnfColType cnfColType ;
	@Column ( name = "valid_regex" , length = 200 )
	private String validRegex ;
	@ManyToOne
	@JoinColumn ( name = "cnf_file_general" )
	private CnfFileGeneral cnfFileGeneral ;
	@Column ( name = "active" , length = 1 )
	private String active ;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id ;
	@Column ( name = "csv_column" , length = 100 )
	private String csvColumn ;
	@Column ( name = "data_logic" , length = 1000 )
	private String dataLogic ;
	@Column ( name = "max_len" )
	private int maxLen ;

	public CnfColType getCnfColType () {
		return this.cnfColType ;
	}
	public void setCnfColType (CnfColType cnfColType) {
		this.cnfColType = cnfColType ;
	}
	public String getValidRegex () {
		return this.validRegex ;
	}
	public void setValidRegex (String validRegex) {
		this.validRegex = validRegex ;
	}
	public CnfFileGeneral getCnfFileGeneral () {
		return this.cnfFileGeneral ;
	}
	public void setCnfFileGeneral (CnfFileGeneral cnfFileGeneral) {
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
	public String getCsvColumn () {
		return this.csvColumn ;
	}
	public void setCsvColumn (String csvColumn) {
		this.csvColumn = csvColumn ;
	}
	public String getDataLogic () {
		return this.dataLogic ;
	}
	public void setDataLogic (String dataLogic) {
		this.dataLogic = dataLogic ;
	}
	public int getMaxLen () {
		return this.maxLen ;
	}
	public void setMaxLen (int maxLen) {
		this.maxLen = maxLen ;
	}
}