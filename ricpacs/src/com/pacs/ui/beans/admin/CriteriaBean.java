package com.pacs.ui.beans.admin;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.pacs.utils.Environment;
import com.pacs.utils.MessageConstants;


@ManagedBean(name= "crit")
@SessionScoped
public class CriteriaBean 
{
	private List<SelectItem> twoOptionsList; //Yes, No
	private List<SelectItem> threeOptionsList; //NA, Yes, No
	private List<SelectItem> negPosOptionsList ; //Negative, positive

	private String maxFileSize;
 
	private String pageTitle;
	private String weasisPath;
	public CriteriaBean() 
	{
		// TODO Auto-generated constructor stub
		this.twoOptionsList = new ArrayList<SelectItem>();
		this.threeOptionsList = new ArrayList<SelectItem>();
		this.negPosOptionsList = new ArrayList<SelectItem>();
		this.weasisPath = Environment.getWeasisServerPath();

	}
	
	
	

	public List<SelectItem> getTwoOptionsList() 
	{
		if(this.twoOptionsList.size()==0)
		{
			this.twoOptionsList.add(new SelectItem("",MessageConstants.Constants.SELECT_ONE_STRING));
			this.twoOptionsList.add(new SelectItem(MessageConstants.Constants.YES_STRING,MessageConstants.Constants.YES_STRING));
			this.twoOptionsList.add(new SelectItem(MessageConstants.Constants.NO_STRING,MessageConstants.Constants.NO_STRING));
		}
		return twoOptionsList;
	}

	public void setTwoOptionsList(List<SelectItem> twoOptionsList) {
		this.twoOptionsList = twoOptionsList;
	}




	public List<SelectItem> getNegPosOptionsList()
	{
		if(this.negPosOptionsList.size() == 0)
		{
			this.negPosOptionsList.add(new SelectItem("", MessageConstants.Constants.SELECT_ONE_STRING));
			this.negPosOptionsList.add(new SelectItem(MessageConstants.Constants.NEGATIVE_STRING, MessageConstants.Constants.NEGATIVE_STRING));
			this.negPosOptionsList.add(new SelectItem(MessageConstants.Constants.POSITIVE_STRING, MessageConstants.Constants.POSITIVE_STRING));
		}
		return negPosOptionsList;
	}




	public void setNegPosOptionsList(List<SelectItem> negPosOptionsList) {
		this.negPosOptionsList = negPosOptionsList;
	}




	public String getPageTitle() {
		return pageTitle;
	}




	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}




	public String getMaxFileSize() 
	{
		if(maxFileSize==null || maxFileSize.trim().length()==0)
		{
			maxFileSize = Environment.getFileMaxSize();
		}
		return maxFileSize;
	}




	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}


	public List<SelectItem> getThreeOptionsList() 
	{
		if(this.threeOptionsList.size()==0)
		{
			this.threeOptionsList.add(new SelectItem(MessageConstants.Constants.NA_STRING,MessageConstants.Constants.NA_STRING));
			this.threeOptionsList.add(new SelectItem(MessageConstants.Constants.YES_STRING,MessageConstants.Constants.YES_STRING));
			this.threeOptionsList.add(new SelectItem(MessageConstants.Constants.NO_STRING,MessageConstants.Constants.NO_STRING));
		}
		return threeOptionsList;
	}




	public void setThreeOptionsList(List<SelectItem> threeOptionsList) {
		this.threeOptionsList = threeOptionsList;
	}




	public String getWeasisPath() {
		return weasisPath;
	}




	public void setWeasisPath(String weasisPath) {
		this.weasisPath = weasisPath;
	}
	
	

}
