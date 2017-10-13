package com.pacs.dal.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.pacs.dal.dao.vw.StudyFilePathVw;

@Entity
@Table(name = "study")
public class Study 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id;
	
	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn (name = "patient_fk")
	private Patient patientFk;
	
	@ManyToOne 
	@JoinColumn (name = "accno_issuer_fk")
	private Issuer accnoIssuerFk;
	
	@Column(name="study_iuid")
	private String studyIuid;
	
	@Column(name="study_id")
	private String studyId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="study_datetime")
	private Date studyDateTime;
	
	@Column(name="accession_no")
	private String accessionNo;
	
	@Column(name="ref_physician")
	private String refPhysician;
	
	@Column(name="ref_phys_fn_sx")
	private String refPhysFnSx;
	
	@Column(name="ref_phys_gn_sx")
	private String refPhysGnSx;
	
	@Column(name="ref_phys_i_name")
	private String refPhysIName;
	
	@Column(name="ref_phys_p_name")
	private String refPhysPName;
	
	@Column(name="study_desc")
	private String studyDesc;
	
	@Column(name="study_custom1")
	private String studyCustom1;
	
	@Column(name="study_custom2")
	private String studyCustom2;
	
	@Column(name="study_custom3")
	private String studyCustom3;
	
	@Column(name="study_status_id")
	private String studyStatusId;
	
	@Column(name="mods_in_study")
	private String modsInStudy;
	
	@ManyToOne 
	@JoinColumn (name = "mods_in_study", insertable=false, updatable=false)
	private LuModalityAlias modalityAlias;
	
	@Column(name="cuids_in_study")
	private String cuidsInStudy;
	
	@Column(name="num_series")
	private Integer numSeries;
	
	@Column(name="num_instances")
	private Integer numInstances;
	
	@Column(name="ext_retr_aet")
	private String extRetrAet;
	
	@Column(name="retrieve_aets")
	private String retrieveAets;
	
	@Column(name="fileset_iuid")
	private String fileSetIuid;
	
	@Column(name="fileset_id")
	private String fileSetId;
	
	@Column(name="availability")
	private Integer availability;
	
	@Column(name="study_status")
	private Integer studyStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="checked_time")
	private Date checkedTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time")
	private Date updatedTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	private Date createdTime;
	
	@Column(name= "study_attrs")
	private byte[] studyAttrs;
	
	@Column(name="is_delete")
	private String isDelete;
	
	@OneToMany(mappedBy = "studyFk" , cascade = CascadeType.ALL)
//	@OrderBy("id DESC")
	private  List<Series> seriesFk;
	
	@OneToMany(mappedBy = "studyFk" , cascade = CascadeType.ALL)
//	@OrderBy("id DESC")
	private  List<StudyOnFs> studyOnFsFk;
	
	@OneToMany(mappedBy = "studyObj")
	private  List<StudyFilePathVw> studyFiles;
	
	@Transient
	private boolean selectedForAction;
	
	@Transient
	private String syncStatus;
	
	public Study() 
	{
		// TODO Auto-generated constructor stub
		this.selectedForAction = false;
		this.syncStatus="";
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


	public String getStudyIuid() {
		return studyIuid;
	}


	public void setStudyIuid(String studyIuid) {
		this.studyIuid = studyIuid;
	}


	public String getStudyId() {
		return studyId;
	}


	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}


	public Date getStudyDateTime() {
		return studyDateTime;
	}


	public void setStudyDateTime(Date studyDateTime) {
		this.studyDateTime = studyDateTime;
	}


	public String getAccessionNo() {
		return accessionNo;
	}


	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}


	public String getRefPhysician() {
		return refPhysician;
	}


	public void setRefPhysician(String refPhysician) {
		this.refPhysician = refPhysician;
	}


	public String getRefPhysFnSx() {
		return refPhysFnSx;
	}


	public void setRefPhysFnSx(String refPhysFnSx) {
		this.refPhysFnSx = refPhysFnSx;
	}


	public String getRefPhysGnSx() {
		return refPhysGnSx;
	}


	public void setRefPhysGnSx(String refPhysGnSx) {
		this.refPhysGnSx = refPhysGnSx;
	}


	public String getRefPhysIName() {
		return refPhysIName;
	}


	public void setRefPhysIName(String refPhysIName) {
		this.refPhysIName = refPhysIName;
	}


	public String getRefPhysPName() {
		return refPhysPName;
	}


	public void setRefPhysPName(String refPhysPName) {
		this.refPhysPName = refPhysPName;
	}


	public String getStudyDesc() {
		return studyDesc;
	}


	public void setStudyDesc(String studyDesc) {
		this.studyDesc = studyDesc;
	}


	public String getStudyCustom1() {
		return studyCustom1;
	}


	public void setStudyCustom1(String studyCustom1) {
		this.studyCustom1 = studyCustom1;
	}


	public String getStudyCustom2() {
		return studyCustom2;
	}


	public void setStudyCustom2(String studyCustom2) {
		this.studyCustom2 = studyCustom2;
	}


	public String getStudyCustom3() {
		return studyCustom3;
	}


	public void setStudyCustom3(String studyCustom3) {
		this.studyCustom3 = studyCustom3;
	}


	public String getStudyStatusId() {
		return studyStatusId;
	}


	public void setStudyStatusId(String studyStatusId) {
		this.studyStatusId = studyStatusId;
	}


	public String getModsInStudy() {
		return modsInStudy;
	}


	public void setModsInStudy(String modsInStudy) {
		this.modsInStudy = modsInStudy;
	}


	public String getCuidsInStudy() {
		return cuidsInStudy;
	}


	public void setCuidsInStudy(String cuidsInStudy) {
		this.cuidsInStudy = cuidsInStudy;
	}


	public Integer getNumSeries() {
		return numSeries;
	}


	public void setNumSeries(Integer numSeries) {
		this.numSeries = numSeries;
	}


	public Integer getNumInstances() {
		return numInstances;
	}


	public void setNumInstances(Integer numInstances) {
		this.numInstances = numInstances;
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


	public String getFileSetIuid() {
		return fileSetIuid;
	}


	public void setFileSetIuid(String fileSetIuid) {
		this.fileSetIuid = fileSetIuid;
	}


	public String getFileSetId() {
		return fileSetId;
	}


	public void setFileSetId(String fileSetId) {
		this.fileSetId = fileSetId;
	}


	public Integer getAvailability() {
		return availability;
	}


	public void setAvailability(Integer availability) {
		this.availability = availability;
	}


	public Integer getStudyStatus() {
		return studyStatus;
	}


	public void setStudyStatus(Integer studyStatus) {
		this.studyStatus = studyStatus;
	}


	public Date getCheckedTime() {
		return checkedTime;
	}


	public void setCheckedTime(Date checkedTime) {
		this.checkedTime = checkedTime;
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


	public byte[] getStudyAttrs() {
		return studyAttrs;
	}


	public void setStudyAttrs(byte[] studyAttrs) {
		this.studyAttrs = studyAttrs;
	}


	public Issuer getAccnoIssuerFk() {
		return accnoIssuerFk;
	}


	public void setAccnoIssuerFk(Issuer accnoIssuerFk) {
		this.accnoIssuerFk = accnoIssuerFk;
	}


	public List<Series> getSeriesFk() {
		return seriesFk;
	}


	public void setSeriesFk(List<Series> seriesFk) {
		this.seriesFk = seriesFk;
	}


	public String getIsDelete() {
		return isDelete;
	}


	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}


	public boolean isSelectedForAction() {
		return selectedForAction;
	}


	public void setSelectedForAction(boolean selectedForAction) {
		this.selectedForAction = selectedForAction;
	}


	public List<StudyOnFs> getStudyOnFsFk() {
		return studyOnFsFk;
	}


	public void setStudyOnFsFk(List<StudyOnFs> studyOnFsFk) {
		this.studyOnFsFk = studyOnFsFk;
	}


	public LuModalityAlias getModalityAlias() {
		return modalityAlias;
	}


	public void setModalityAlias(LuModalityAlias modalityAlias) {
		this.modalityAlias = modalityAlias;
	}


	public String getSyncStatus() {
		return syncStatus;
	}


	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}


	public List<StudyFilePathVw> getStudyFiles() {
		return studyFiles;
	}


	public void setStudyFiles(List<StudyFilePathVw> studyFiles) {
		this.studyFiles = studyFiles;
	}
	
	

}
