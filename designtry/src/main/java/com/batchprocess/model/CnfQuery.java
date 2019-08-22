
package com.batchprocess.model ;

import javax.persistence.Column ;
import javax.persistence.Entity ;
import javax.persistence.GenerationType ;
import javax.persistence.Table ;
import javax.persistence.GeneratedValue ;
import javax.persistence.OneToMany ;
import java.util.Set ;
import com.common.behaviour.ModelBase ;
import javax.persistence.Id ;
import com.batchprocess.model.CnfFileGeneral ;

@Entity
@Table ( name = "cnf_query" )
public class CnfQuery implements ModelBase {

	@Column ( name = "sql_query" , length = 500 )
	private String sqlQuery ;
	@Column ( name = "name" , length = 50 )
	private String name ;
	@OneToMany ( mappedBy="cnfQuery" )
	private Set<CnfFileGeneral> cnfFileGeneral ;
	@Column ( name = "active" , length = 1 )
	private String active ;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id ;
	@Column ( name = "data_source" , length = 50 )
	private String dataSource ;

	public String getSqlQuery () {
		return this.sqlQuery ;
	}
	public void setSqlQuery (String sqlQuery) {
		this.sqlQuery = sqlQuery ;
	}
	public String getName () {
		return this.name ;
	}
	public void setName (String name) {
		this.name = name ;
	}
	public Set<CnfFileGeneral> getCnfFileGeneral () {
		return this.cnfFileGeneral ;
	}
	public void setCnfFileGeneral (Set<CnfFileGeneral> cnfFileGeneral) {
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
	public String getDataSource () {
		return this.dataSource ;
	}
	public void setDataSource (String dataSource) {
		this.dataSource = dataSource ;
	}
}