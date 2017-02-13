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
@Table(name = "series")
public class Series 
{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id;
	
	@ManyToOne 
	@JoinColumn (name = "study_fk")
	private Study studyFk;
	
	@ManyToOne 
	@JoinColumn (name = "mpps_fk")
	private Mpps mppsFk;
	
	@ManyToOne 
	@JoinColumn (name = "inst_code_fk")
	private Code instCodeFk;
	
	@Column(name="series_iuid")
	private String seriesIuid;
	
	@Column(name="series_no")
	private String seriesNo;
	
	@Column(name="modality")
	private String modality;
	
	@Column(name="body_part")
	private String bodyPart;
	
	@Column(name="laterality")
	private String laterality;
	
	@Column(name="series_desc")
	private String seriesDesc;
	
	@Column(name="institution")
	private String institution;
	
	@Column(name="station_name")
	private String stationName;
	
	@Column(name="department")
	private String department;
	
	@Column(name="perf_physician")
	private String perfPhysician;
	
	@Column(name="perf_phys_fn_sx")
	private String perfPhysFnSx;
	
	@Column(name="perf_phys_gn_sx")
	private String perfPhysGnSx;
	
	@Column(name="perf_phys_i_name")
	private String perfPhysIName;
	
	@Column(name="perf_phys_p_name")
	private String perfPhysPName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="pps_start")
	private Date ppsStart;
	
	@Column(name="pps_iuid")
	private String ppsIuid;
	
	@Column(name="series_custom1")
	private String seriesCustom1;
	
	@Column(name="series_custom2")
	private String seriesCustom2;
	
	@Column(name="series_custom3")
	private String seriesCustom3;
	
	@Column(name="num_instances")
	private Integer numInstances;
	
	@Column(name="src_aet")
	private String srcAet;
	
	@Column(name="ext_retr_aet")
	private String extRetrAet;
	
	@Column(name="retrieve_aets")
	private String retrieveAets;
	
	@Column(name="fileset_iuid")
	private String filesetIuid;
	
	@Column(name="availability")
	private Integer availability;
	
	@Column(name="series_status")
	private Integer seriesStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time")
	private Date updatedTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;
	
	@Column(name= "series_attrs")
	private byte[] seriesAttrs;
	
	public Series() 
	{
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Study getStudyFk() {
		return studyFk;
	}

	public void setStudyFk(Study studyFk) {
		this.studyFk = studyFk;
	}

	public Mpps getMppsFk() {
		return mppsFk;
	}

	public void setMppsFk(Mpps mppsFk) {
		this.mppsFk = mppsFk;
	}

	public Code getInstCodeFk() {
		return instCodeFk;
	}

	public void setInstCodeFk(Code instCodeFk) {
		this.instCodeFk = instCodeFk;
	}

	public String getSeriesIuid() {
		return seriesIuid;
	}

	public void setSeriesIuid(String seriesIuid) {
		this.seriesIuid = seriesIuid;
	}
	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getBodyPart() {
		return bodyPart;
	}

	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}

	public String getLaterality() {
		return laterality;
	}

	public void setLaterality(String laterality) {
		this.laterality = laterality;
	}

	public String getSeriesDesc() {
		return seriesDesc;
	}

	public void setSeriesDesc(String seriesDesc) {
		this.seriesDesc = seriesDesc;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPerfPhysician() {
		return perfPhysician;
	}

	public void setPerfPhysician(String perfPhysician) {
		this.perfPhysician = perfPhysician;
	}

	public String getPerfPhysFnSx() {
		return perfPhysFnSx;
	}

	public void setPerfPhysFnSx(String perfPhysFnSx) {
		this.perfPhysFnSx = perfPhysFnSx;
	}

	public String getPerfPhysGnSx() {
		return perfPhysGnSx;
	}

	public void setPerfPhysGnSx(String perfPhysGnSx) {
		this.perfPhysGnSx = perfPhysGnSx;
	}

	public String getPerfPhysIName() {
		return perfPhysIName;
	}

	public void setPerfPhysIName(String perfPhysIName) {
		this.perfPhysIName = perfPhysIName;
	}

	public String getPerfPhysPName() {
		return perfPhysPName;
	}

	public void setPerfPhysPName(String perfPhysPName) {
		this.perfPhysPName = perfPhysPName;
	}

	public Date getPpsStart() {
		return ppsStart;
	}

	public void setPpsStart(Date ppsStart) {
		this.ppsStart = ppsStart;
	}

	public String getPpsIuid() {
		return ppsIuid;
	}

	public void setPpsIuid(String ppsIuid) {
		this.ppsIuid = ppsIuid;
	}

	public String getSeriesCustom1() {
		return seriesCustom1;
	}

	public void setSeriesCustom1(String seriesCustom1) {
		this.seriesCustom1 = seriesCustom1;
	}

	public String getSeriesCustom2() {
		return seriesCustom2;
	}

	public void setSeriesCustom2(String seriesCustom2) {
		this.seriesCustom2 = seriesCustom2;
	}

	public String getSeriesCustom3() {
		return seriesCustom3;
	}

	public void setSeriesCustom3(String seriesCustom3) {
		this.seriesCustom3 = seriesCustom3;
	}

	public Integer getNumInstances() {
		return numInstances;
	}

	public void setNumInstances(Integer numInstances) {
		this.numInstances = numInstances;
	}

	public String getSrcAet() {
		return srcAet;
	}

	public void setSrcAet(String srcAet) {
		this.srcAet = srcAet;
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

	public String getFilesetIuid() {
		return filesetIuid;
	}

	public void setFilesetIuid(String filesetIuid) {
		this.filesetIuid = filesetIuid;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public Integer getSeriesStatus() {
		return seriesStatus;
	}

	public void setSeriesStatus(Integer seriesStatus) {
		this.seriesStatus = seriesStatus;
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

	public byte[] getSeriesAttrs() {
		return seriesAttrs;
	}

	public void setSeriesAttrs(byte[] seriesAttrs) {
		this.seriesAttrs = seriesAttrs;
	}

	public String getSeriesNo() {
		return seriesNo;
	}

	public void setSeriesNo(String seriesNo) {
		this.seriesNo = seriesNo;
	}
}
