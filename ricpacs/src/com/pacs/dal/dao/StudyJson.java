/**
 * 
 */
package com.pacs.dal.dao;

/**
 * @author Analysis
 *
 */
public class StudyJson {
	
	private String patientName;
	private String patientMRNo;
	private String studyId;
	private String modality;
	private String dateTime;
	private String studyUrl;
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientMRNo() {
		return patientMRNo;
	}
	public void setPatientMRNo(String patientMRNo) {
		this.patientMRNo = patientMRNo;
	}
	public String getStudyId() {
		return studyId;
	}
	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}
	public String getModality() {
		return modality;
	}
	public void setModality(String modality) {
		this.modality = modality;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getStudyUrl() {
		return studyUrl;
	}
	public void setStudyUrl(String studyUrl) {
		this.studyUrl = studyUrl;
	}

}
