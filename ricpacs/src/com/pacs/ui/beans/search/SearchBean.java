package com.pacs.ui.beans.search;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.pacs.bll.search.SearchBll;
import com.pacs.dal.dao.Patient;
import com.pacs.dal.dao.Series;
import com.pacs.dal.dao.Study;
import com.pacs.utils.MessageConstants;
import com.pacs.utils.MessageUtils;
import com.pacs.utils.NavigationConstants;


@ManagedBean(name="searchBean")
@SessionScoped
public class SearchBean 
{
	
	private Patient toSearchPatient;
	private Study toSearchStudy;
	private Date dateFrom;
	private Date dateTo;
	
	private String testValue;
	private Patient selectedPatient;
	private Study selectedStudy;
	private List<Patient> patientsList = new ArrayList<Patient>();
	private List<Study> studiesList = new ArrayList<Study>();
	private List<Series> seriesList = new ArrayList<Series>();
	private List<Study> studiesListMain = new ArrayList<Study>();
	
	
	public SearchBean() 
	{
		// TODO Auto-generated constructor stub
		this.toSearchPatient = new Patient();
		this.toSearchStudy = new Study();
//		Calendar calendar = Calendar.getInstance();	
//		this.dateTo =calendar.getTime();
//		calendar.add(Calendar.MONTH, -1);
//		this.dateFrom = calendar.getTime();
		this.testValue = "";
		this.selectedPatient = new Patient();
		this.selectedStudy = new Study();
	}


//	Business Methods
	
	
	public void testMethod()
	{
		System.out.println("In test method");
		this.testValue = this.testValue+" ,,,,";
//		this.studiesList.clear();
//		this.studiesList.addAll(this.selectedPatient.getStudiesFk());
//		
//		return testValue;
	}
	
	public String viewStudyDetails()
	{
		System.out.println("In viewStudyDetails method");
		
		this.studiesList.clear();
		this.studiesList.addAll(this.selectedPatient.getStudiesFk());
		
//		return "/pages/search/details/studyDetails.xhtml?faces-redirect=true";
		return NavigationConstants.STUDY_DETAILS_NAVIGATION;
	}

	public String viewSeriesDetails()
	{
		System.out.println("In viewSeriesDetails method");
		
		this.seriesList.clear();
		this.seriesList.addAll(this.selectedStudy.getSeriesFk());
		
		return NavigationConstants.SERIES_DETAILS_NAVIGATION;
	}
	
	public String populateSeriesDataForDialog()
	{
		System.out.println("In populateSeriesDataForDialog method, selectedstudy is ="+this.selectedStudy.getId());
		this.seriesList.clear();
		this.seriesList.addAll(this.selectedStudy.getSeriesFk());

		return "";
		
	}
	
	public String searchPatientsData()
	{
		SearchBll bll = new SearchBll();
		System.out.println("In searchPatientsData method");
		
//		if(dateTo.after(dateFrom))
		{
			this.patientsList = bll.searchPatients(toSearchPatient, dateFrom, dateTo);
			System.out.println("Patients list size :: " + this.patientsList.size());
		
		}
//		else
//		{
//			MessageUtils.error(MessageConstants.Messages.INVALID_DATE);
//		}
		
		
		
		return "";
	}
	
	public String searchStudyData()
	{
		SearchBll bll = new SearchBll();
		System.out.println("In searchStudyData method");
		
		if(validateStudyDates())
		{
			this.studiesListMain = bll.searchStudy(toSearchPatient, toSearchStudy, dateFrom, dateTo);
			System.out.println("Study list size :: " + this.studiesListMain.size());
		
		}
		else
		{
			MessageUtils.error(MessageConstants.Messages.INVALID_DATE);
		}
		
		
		
		return "";
	}
	
	private Boolean validateStudyDates()
	{
		if(this.dateFrom == null && this.dateTo == null)
		{
			return true;
		}
		else if(this.dateFrom != null && this.dateTo != null && this.dateFrom.after(dateTo))
		{
			System.out.println("dateFrom.after dateTo");
			return false;
		} 
		
		return true;
	}
	
	
	
	
//	Getters and Setters
	
	
	public Patient getToSearchPatient() {
		return toSearchPatient;
	}


	public void setToSearchPatient(Patient toSearchPatient) {
		this.toSearchPatient = toSearchPatient;
	}


	public Date getDateFrom() {
		return dateFrom;
	}


	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}


	public Date getDateTo() {
		return dateTo;
	}


	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}


	public List<Patient> getPatientsList() {
		return patientsList;
	}


	public void setPatientsList(List<Patient> patientsList) {
		this.patientsList = patientsList;
	}


	public String getTestValue() {
		return testValue;
	}


	public void setTestValue(String testValue) {
		this.testValue = testValue;
	}


	public Patient getSelectedPatient() {
		return selectedPatient;
	}


	public void setSelectedPatient(Patient selectedPatient) {
		this.selectedPatient = selectedPatient;
	}


	public List<Study> getStudiesList() {
		return studiesList;
	}


	public void setStudiesList(List<Study> studiesList) {
		this.studiesList = studiesList;
	}


	public Study getSelectedStudy() {
		return selectedStudy;
	}


	public void setSelectedStudy(Study selectedStudy) {
		this.selectedStudy = selectedStudy;
	}


	public List<Series> getSeriesList() {
		return seriesList;
	}


	public void setSeriesList(List<Series> seriesList) {
		this.seriesList = seriesList;
	}


	public Study getToSearchStudy() {
		return toSearchStudy;
	}


	public void setToSearchStudy(Study toSearchStudy) {
		this.toSearchStudy = toSearchStudy;
	}


	public List<Study> getStudiesListMain() {
		return studiesListMain;
	}


	public void setStudiesListMain(List<Study> studiesListMain) {
		this.studiesListMain = studiesListMain;
	}
	
	

}
