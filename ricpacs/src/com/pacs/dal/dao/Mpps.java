package com.pacs.dal.dao;

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

@Entity
@Table(name = "mpps")
public class Mpps 
{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id;
	
	@ManyToOne 
	@JoinColumn (name = "patient_fk")
	private Patient patientFk;
	
	@ManyToOne 
	@JoinColumn (name = "drcode_fk")
	private Code drcodeFk;
	
	@Column(name="mpps_iuid")
	private String mppsIuId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="pps_start")
	private Date ppsStart;
	
	@Column(name="station_aet")
	private String stationAet;
	
	@Column(name="modality")
	private String modality;
	
	@Column(name="accession_no")
	private String accessionNo;
	
	@Column(name="mpps_status")
	private Integer mppsStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time")
	private Date updatedTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;
	
	@Column(name="mpps_attrs")
	private byte[] mppsAttrs;
	
	
	public Mpps() 
	{
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Patient getPatientFk() {
		return patientFk;
	}


	public void setPatientFk(Patient patientFk) {
		this.patientFk = patientFk;
	}


	public Code getDrcodeFk() {
		return drcodeFk;
	}


	public void setDrcodeFk(Code drcodeFk) {
		this.drcodeFk = drcodeFk;
	}


	public String getMppsIuId() {
		return mppsIuId;
	}


	public void setMppsIuId(String mppsIuId) {
		this.mppsIuId = mppsIuId;
	}


	public Date getPpsStart() {
		return ppsStart;
	}


	public void setPpsStart(Date ppsStart) {
		this.ppsStart = ppsStart;
	}


	public String getStationAet() {
		return stationAet;
	}


	public void setStationAet(String stationAet) {
		this.stationAet = stationAet;
	}


	public String getModality() {
		return modality;
	}


	public void setModality(String modality) {
		this.modality = modality;
	}


	public String getAccessionNo() {
		return accessionNo;
	}


	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}


	public Integer getMppsStatus() {
		return mppsStatus;
	}


	public void setMppsStatus(Integer mppsStatus) {
		this.mppsStatus = mppsStatus;
	}


	public Date getUpdatedTime() {
		return updatedTime;
	}


	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}


	public Date getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}


	public byte[] getMppsAttrs() {
		return mppsAttrs;
	}


	public void setMppsAttrs(byte[] mppsAttrs) {
		this.mppsAttrs = mppsAttrs;
	}

}
