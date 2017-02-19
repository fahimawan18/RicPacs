package com.pacs.dal.dao.priv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "priv_study")
public class PrivStudy 
{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id;

	@ManyToOne 
	@JoinColumn (name = "patient_fk")
	private PrivPatient patientFk;
	
	@Column(name="priv_type")
	private Integer privType;
	
	@Column(name="study_iuid")
	private String studyIuid;
	
	@Column(name="accession_no")
	private String accessionNo;
	
	@Column(name= "study_attrs")
	private byte[] studyAttrs;
	
	public PrivStudy() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PrivPatient getPatientFk() {
		return patientFk;
	}

	public void setPatientFk(PrivPatient patientFk) {
		this.patientFk = patientFk;
	}

	public Integer getPrivType() {
		return privType;
	}

	public void setPrivType(Integer privType) {
		this.privType = privType;
	}

	public String getStudyIuid() {
		return studyIuid;
	}

	public void setStudyIuid(String studyIuid) {
		this.studyIuid = studyIuid;
	}

	public String getAccessionNo() {
		return accessionNo;
	}

	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}

	public byte[] getStudyAttrs() {
		return studyAttrs;
	}

	public void setStudyAttrs(byte[] studyAttrs) {
		this.studyAttrs = studyAttrs;
	}


}
