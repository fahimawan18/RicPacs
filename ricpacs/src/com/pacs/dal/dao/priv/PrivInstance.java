package com.pacs.dal.dao.priv;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pacs.dal.dao.Series;


@Entity
@Table(name = "priv_instance")
public class PrivInstance 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id;
	
	@ManyToOne 
	@JoinColumn (name = "series_fk")
	private PrivSeries seriesFk;
	
	@Column(name="priv_type")
	private Integer privType;
	
	@Column(name="sop_iuid")
	private String sopIuid;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cretaed_time")
	private Date createdTime;
	
	@Column(name= "inst_attrs")
	private byte[] instAttrs;

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

	public String getSopIuid() {
		return sopIuid;
	}

	public void setSopIuid(String sopIuid) {
		this.sopIuid = sopIuid;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public byte[] getInstAttrs() {
		return instAttrs;
	}

	public void setInstAttrs(byte[] instAttrs) {
		this.instAttrs = instAttrs;
	}

}
