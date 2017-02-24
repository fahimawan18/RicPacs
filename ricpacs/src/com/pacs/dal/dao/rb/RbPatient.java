package com.pacs.dal.dao.rb;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "rb_patient")
public class RbPatient 
{
	
	@Id //@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id;
	
	@ManyToOne 
	@JoinColumn (name = "merge_fk")
	private RbPatient mergeFk;
	
	@Column(name="pat_id")
	private String patId;
	
	@Column(name="pat_id_issuer")
	private String patIdIssuer;
	
	@Column(name="pat_name")
	private String patName;
	
	@Column(name="pat_fn_sx")
	private String patFnSx;
	
	@Column(name="pat_gn_sx")
	private String patGnSx;
	
	@Column(name="pat_i_name")
	private String patIName;
	
	@Column(name="pat_p_name")
	private String patPName;
	
	@Column(name="pat_birthdate")
	private String patBirthdate;
	
	@Column(name="pat_sex")
	private String patSex;
	
	@Column(name="pat_custom1")
	private String patCustom1;
	
	@Column(name="pat_custom2")
	private String patCustom2;
	
	@Column(name="pat_custom3")
	private String patCustom3;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time")
	private Date updatedTime;
	
	@Column(name= "pat_attrs")
	private byte[] patAttrs;
	
	@OneToMany(mappedBy = "patientFk" , cascade = CascadeType.ALL)
//	@OrderBy("id DESC")
	private  List<RbStudy> studiesFk;
	
	public RbPatient() 
	{
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPatFnSx() {
		return patFnSx;
	}

	public void setPatFnSx(String patFnSx) {
		this.patFnSx = patFnSx;
	}

	public String getPatGnSx() {
		return patGnSx;
	}

	public void setPatGnSx(String patGnSx) {
		this.patGnSx = patGnSx;
	}

	public String getPatIName() {
		return patIName;
	}

	public void setPatIName(String patIName) {
		this.patIName = patIName;
	}

	public String getPatPName() {
		return patPName;
	}

	public void setPatPName(String patPName) {
		this.patPName = patPName;
	}

	public String getPatBirthdate() {
		return patBirthdate;
	}

	public void setPatBirthdate(String patBirthdate) {
		this.patBirthdate = patBirthdate;
	}

	public String getPatSex() {
		return patSex;
	}

	public void setPatSex(String patSex) {
		this.patSex = patSex;
	}

	public String getPatCustom1() {
		return patCustom1;
	}

	public void setPatCustom1(String patCustom1) {
		this.patCustom1 = patCustom1;
	}

	public String getPatCustom2() {
		return patCustom2;
	}

	public void setPatCustom2(String patCustom2) {
		this.patCustom2 = patCustom2;
	}

	public String getPatCustom3() {
		return patCustom3;
	}

	public void setPatCustom3(String patCustom3) {
		this.patCustom3 = patCustom3;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public byte[] getPatAttrs() {
		return patAttrs;
	}

	public void setPatAttrs(byte[] patAttrs) {
		this.patAttrs = patAttrs;
	}

	public List<RbStudy> getStudiesFk() {
		return studiesFk;
	}

	public void setStudiesFk(List<RbStudy> studiesFk) {
		this.studiesFk = studiesFk;
	}

	public RbPatient getMergeFk() {
		return mergeFk;
	}

	public void setMergeFk(RbPatient mergeFk) {
		this.mergeFk = mergeFk;
	}
	
	

}
