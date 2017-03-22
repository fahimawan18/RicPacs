package com.pacs.bll.admin;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import com.pacs.dal.dao.FileSystem;
import com.pacs.dal.dao.vw.LuAetVw;
import com.pacs.dal.dao.vw.LuModalityVw;
import com.pacs.utils.HibernateUtilsAnnot;
import com.pacs.utils.MessageConstants;

public class CriteriaBll 
{
	
	public CriteriaBll() 
	{
		// TODO Auto-generated constructor stub
	}
	
	
	public String getOnlineStorageDrive()
	{
		
		Session session = null;
		String drive = "c:";
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			Criteria cr=session.createCriteria(FileSystem.class);
			ProjectionList proList=Projections.projectionList();
			proList.add(Projections.property("dirPath"));
			cr.setProjection(proList);
			if(cr.list().size()>0)
			{
				drive = (String)cr.list().get(0);
			}
			System.out.println("file system drive ="+drive);
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();			
		}
		return drive.substring(0, 2);
	}
	
	public List<SelectItem> getSrcAetSelectItemList()
	{
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<LuAetVw> listTemp = new ArrayList<LuAetVw>();
		
		Session session = null;
		try
		{
//			list.add(new SelectItem(MessageConstants.Constants.ALL_STRING));
//			list.add(new SelectItem(MessageConstants.Constants.NONE_STRING));
			session = HibernateUtilsAnnot.currentSession();
			Criteria cr=session.createCriteria(LuAetVw.class);
			listTemp = cr.list();
			
			for(LuAetVw a:listTemp)
			{
				list.add(new SelectItem(a.getSrcAet(),a.getSrcAet()));
			}
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();			
		}
		return list;
	}
	
	public List<SelectItem> getModalitySelectItemList()
	{
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<LuModalityVw> listTemp = new ArrayList<LuModalityVw>();
		
		Session session = null;
		try
		{
//			list.add(new SelectItem(MessageConstants.Constants.ALL_STRING));
//			list.add(new SelectItem(MessageConstants.Constants.NONE_STRING));
			session = HibernateUtilsAnnot.currentSession();
			Criteria cr=session.createCriteria(LuModalityVw.class);
			listTemp = cr.list();
			
			for(LuModalityVw a:listTemp)
			{
				list.add(new SelectItem(a.getModality(),a.getModality()));
			}
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();			
		}
		return list;
	}

}
