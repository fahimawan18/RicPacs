package com.pacs.dal.dao.priv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "priv_patient")
public class PrivPatient 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id;
	
	@Column(name = "priv_type")
	private Integer privType;
	
	@Column(name = "pat_id")
	private String patId;
	
	@Column(name = "pat_id_issuer")
	private String patIdIssuer;
	
	@Column(name = "pat_Name")
	private String patName;
	
	@Column(name= "pat_attrs")
	private byte[] patAttrs;
	
	public PrivPatient() 
	{
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrivType() {
		return privType;
	}

	public void setPrivType(Integer privType) {
		this.privType = privType;
	}

	public String getPatId() {
		return patId;
	}

	public void setPatId(String patId) {
		this.patId = patId;
	}

	public String getPatIdIssuer() {
		return patIdIssuer;
	}

	public void setPatIdIssuer(String patIdIssuer) {
		this.patIdIssuer = patIdIssuer;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public byte[] getPatAttrs() {
		return patAttrs;
	}

	public void setPatAttrs(byte[] patAttrs) {
		this.patAttrs = patAttrs;
	}
	
	

}
