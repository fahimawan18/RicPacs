package com.pacs.ui.beans.search;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.util.Base64;

import com.google.gson.Gson;
import com.iac.web.util.FacesUtils;
import com.pacs.bll.search.SearchBll;
import com.pacs.dal.dao.ApplicationUsers;
import com.pacs.dal.dao.Patient;
import com.pacs.dal.dao.Series;
import com.pacs.dal.dao.Study;
import com.pacs.dal.dao.StudyJson;
import com.pacs.ui.beans.UserBean;
import com.pacs.ui.beans.admin.CriteriaBean;
import com.pacs.utils.Environment;
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
		studiesListMain = new ArrayList<Study>();
		if(validateStudyDates())
		{
			this.studiesListMain = bll.searchStudy(toSearchPatient, toSearchStudy, dateFrom, dateTo,
				MessageConstants.Constants.NO_STRING);
			System.out.println("Study list size :: " + this.studiesListMain.size());
		
		}
		else
		{
			MessageUtils.error(MessageConstants.Messages.INVALID_DATE);
		}
		
		
		
		return "";
	}
	
	public String searchDeletedStudyData()
	{
		SearchBll bll = new SearchBll();
		System.out.println("In searchDeletedStudyData method");
		studiesListMain = new ArrayList<Study>();
		if(validateStudyDates())
		{
			this.studiesListMain = bll.searchStudy(toSearchPatient, toSearchStudy, dateFrom, dateTo,
					MessageConstants.Constants.YES_STRING);
			System.out.println("Deleted Study list size :: " + this.studiesListMain.size());
		
		}
		else
		{
			MessageUtils.error(MessageConstants.Messages.INVALID_DATE);
		}
		
		
		
		return "";
	}
	
	public String deleteStudyData()
	{
		SearchBll bll = new SearchBll();
		System.out.println("In deleteStudyData method");
		
		System.out.println(this.selectedStudy.getStudyDesc());
		if(bll.deleteStudyData(this.selectedStudy))
		{
			MessageUtils.info("Study data moved to Recycle Bin successfully");
//			searchStudyData();
			this.studiesListMain.remove(this.selectedStudy);
		}
		else
		{
			MessageUtils.error("Error deleting data");
		}
		return "";
	}
	
	public String deletePermStudyData()
	{
		SearchBll bll = new SearchBll();
		System.out.println("In deletePermStudyData method");
		
		
		if(bll.deletePermStudyData(this.studiesListMain))
		{
			MessageUtils.info("Study data deleted permanently");
			searchDeletedStudyData();
			
		}
		else
		{
			MessageUtils.error("Error deleting data");
		}
		return "";
	}
	
	public String restoreStudyData()
	{
		SearchBll bll = new SearchBll();
		System.out.println("In restoreStudyData method");
		
		
		if(bll.restoreStudyData(this.studiesListMain))
		{
			MessageUtils.info("Study data restored successfully");
			searchDeletedStudyData();
			
		}
		else
		{
			MessageUtils.error("Error restoring data");
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
	
	
	public void renderJson() throws IOException {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = facesContext.getExternalContext();
	    Map<String, String> requestParams = externalContext.getRequestParameterMap();
	    String passwword = requestParams.get("PASSWORD");
	    String encodedPasswword = requestParams.get("ENCODED_PASSWORD");
	    System.out.println("Received MR No :: " + requestParams.get("MRNO")) ;
	    System.out.println("Received Username :: " + requestParams.get("USERNAME")) ;
	    System.out.println("Received Password :: " + requestParams.get("PASSWORD")) ;
	    System.out.println("Received encoded Password :: " + requestParams.get("ENCODED_PASSWORD")) ;
	    String decoded  = "";
//	    String encoded = Base64.encodeToString(requestParams.get("PASSWORD").getBytes(), false);
//	    System.out.println("Encoded :: " + encoded ) ; 
	    if(encodedPasswword!=null && encodedPasswword.length()>0)
	    {
	    	decoded = new String(Base64.decode(encodedPasswword));
	    	System.out.println(" Decoded Password :: " + decoded) ;
	    }
	    
	    
	    UserBean userBean = new UserBean();
	    
	    ApplicationUsers loginUser =  new ApplicationUsers();
	    loginUser.setUserId(requestParams.get("USERNAME"));
	    if(passwword!=null && passwword.length()>0)
	    	loginUser.setPassword(passwword);
	    else
	    	loginUser.setPassword(decoded);	
	    userBean.setToSearchUser(loginUser);
	    String returnString = userBean.loginAction();
	    if(returnString.equals(NavigationConstants.HOME_NAVIGATION)){
	    	externalContext.setResponseContentType("application/json");
		    externalContext.setResponseCharacterEncoding("UTF-8");
		    
		    SearchBll bll = new SearchBll();
		    Patient patient = new Patient();
		    patient.setPatId(requestParams.get("MRNO"));
		    List<Study> studyList = bll.searchStudy(patient, null, null, null,MessageConstants.Constants.NO_STRING);
			System.out.println("Study list size :: " + studyList.size());
		    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    String path = Environment.getWeasisServerPath();
		    List<StudyJson> studies = new ArrayList<StudyJson>();
		    StudyJson studyJson = null;
		    for (Study study : studyList) {
		    	studyJson = new StudyJson();
		    	studyJson.setPatientName( study.getPatientFk().getPatName());
		    	studyJson.setPatientMRNo( study.getPatientFk().getPatId());
		    	if(study.getStudyId()!=null)
		    		studyJson.setStudyId( study.getStudyId());
		    	else
		    		studyJson.setStudyId( "");
		    	if(study.getStudyDateTime()!=null)
		    		studyJson.setDateTime( format.format(study.getStudyDateTime()));
		    	else
		    		studyJson.setDateTime("");
		    	studyJson.setModality( study.getModsInStudy());
		    	studyJson.setStudyUrl( path + study.getStudyIuid());
				
		    	studies.add(studyJson);
			}
		    Gson gson = new Gson();
		    String json = gson.toJson(studies);
		    
		    externalContext.getResponseOutputWriter().write(json);
	    }
	    
	    else
	    {
	    	
	    	 FacesMessage message =  FacesContext.getCurrentInstance().getMessages().next();
	    	 externalContext.getResponseOutputWriter().write(message.getSummary()+ message.getDetail());
	    }
	    
	    
	    
	    facesContext.responseComplete();
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
