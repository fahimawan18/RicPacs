package com.pacs.ui.beans.search;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.pacs.bll.search.SearchBll;
import com.pacs.dal.dao.Patient;


@ManagedBean(name="searchBean")
@SessionScoped
public class SearchBean 
{
	
	private Patient toSearchPatient;
	private Date dateFrom;
	private Date dateTo;
	
	private List<Patient> patientsList = new ArrayList<Patient>();
	
	
	public SearchBean() 
	{
		// TODO Auto-generated constructor stub
		this.toSearchPatient = new Patient();
		Calendar calendar = Calendar.getInstance();	
		this.dateTo =calendar.getTime();
		calendar.add(Calendar.MONTH, -1);
		this.dateFrom = calendar.getTime();
	}


//	Business Methods
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
	
	

}
