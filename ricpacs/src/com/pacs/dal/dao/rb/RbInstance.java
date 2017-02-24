package com.pacs.dal.dao.rb;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pacs.dal.dao.Code;

@Entity
@Table(name = "instance")
public class RbInstance 
{
	
	@Id //@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id;
	
	@ManyToOne 
	@JoinColumn (name = "series_fk")
	private RbSeries seriesFk;
	
	@ManyToOne 
	@JoinColumn (name = "srcode_fk")
	private Code srCodeFk;
	
//	@ManyToOne 
//	@JoinColumn (name = "media_fk")
//	private Media mediaFk;
	
	@Column(name="sop_iuid")
	private String sopIuid;
	
	@Column(name="sop_cuid")
	private String sopCuid;
	
	@Column(name="inst_no")
	private String instNo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="content_datetime")
	private Date contentDateTime;

	@Column(name="sr_complete")
	private String srComplete;
	
	@Column(name="sr_verified")
	private String srVerified;
	
	@Column(name="inst_custom1")
	private String instCustom1;
	
	@Column(name="inst_custom2")
	private String instCustom2;
	
	@Column(name="inst_custom3")
	private String instCustom3;
	
	@Column(name="ext_retr_aet")
	private String extRetrAet;
	
	@Column(name="retrieve_aets")
	private String retrieveAets;
	
	@Column(name="availability")
	private Integer availability;
	
	@Column(name="inst_status")
	private Integer instStatus;
	
	@Column(name = "allAttrs", columnDefinition="BIT")
	private Boolean allAttrs;
	
	@Column(name = "commitment", columnDefinition="BIT")
	private Boolean commitment;
	
	@Column(name = "archived", columnDefinition="BIT")
	private Boolean archived;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cretaed_time")
	private Date createdTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time")
	private Date updatedTime;
	
	@Column(name= "inst_attrs")
	private byte[] instAttrs;
	
	public RbInstance() 
	{
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RbSeries getSeriesFk() {
		return seriesFk;
	}

	public void setSeriesFk(RbSeries seriesFk) {
		this.seriesFk = seriesFk;
	}

	public Code getSrCodeFk() {
		return srCodeFk;
	}

	public void setSrCodeFk(Code srCodeFk) {
		this.srCodeFk = srCodeFk;
	}

	public String getSopIuid() {
		return sopIuid;
	}

	public void setSopIuid(String sopIuid) {
		this.sopIuid = sopIuid;
	}

	public String getSopCuid() {
		return sopCuid;
	}

	public void setSopCuid(String sopCuid) {
		this.sopCuid = sopCuid;
	}

	public String getInstNo() {
		return instNo;
	}

	public void setInstNo(String instNo) {
		this.instNo = instNo;
	}

	public Date getContentDateTime() {
		return contentDateTime;
	}

	public void setContentDateTime(Date contentDateTime) {
		this.contentDateTime = contentDateTime;
	}

	public String getSrComplete() {
		return srComplete;
	}

	public void setSrComplete(String srComplete) {
		this.srComplete = srComplete;
	}

	public String getSrVerified() {
		return srVerified;
	}

	public void setSrVerified(String srVerified) {
		this.srVerified = srVerified;
	}

	public String getInstCustom1() {
		return instCustom1;
	}

	public void setInstCustom1(String instCustom1) {
		this.instCustom1 = instCustom1;
	}

	public String getInstCustom2() {
		return instCustom2;
	}

	public void setInstCustom2(String instCustom2) {
		this.instCustom2 = instCustom2;
	}

	public String getInstCustom3() {
		return instCustom3;
	}

	public void setInstCustom3(String instCustom3) {
		this.instCustom3 = instCustom3;
	}

	public String getExtRetrAet() {
		return extRetrAet;
	}

	public void setExtRetrAet(String extRetrAet) {
		this.extRetrAet = extRetrAet;
	}

	public String getRetrieveAets() {
		return retrieveAets;
	}

	public void setRetrieveAets(String retrieveAets) {
		this.retrieveAets = retrieveAets;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public Integer getInstStatus() {
		return instStatus;
	}

	public void setInstStatus(Integer instStatus) {
		this.instStatus = instStatus;
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

	public byte[] getInstAttrs() {
		return instAttrs;
	}

	public void setInstAttrs(byte[] instAttrs) {
		this.instAttrs = instAttrs;
	}

	public Boolean getAllAttrs() {
		return allAttrs;
	}

	public void setAllAttrs(Boolean allAttrs) {
		this.allAttrs = allAttrs;
	}

	public Boolean getCommitment() {
		return commitment;
	}

	public void setCommitment(Boolean commitment) {
		this.commitment = commitment;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}
}
