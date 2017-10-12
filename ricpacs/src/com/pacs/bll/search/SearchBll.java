package com.pacs.bll.search;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.iac.util.StringUtils;
import com.iac.web.util.FacesUtils;
import com.pacs.dal.dao.Files;
import com.pacs.dal.dao.Instance;
import com.pacs.dal.dao.Patient;
import com.pacs.dal.dao.Series;
import com.pacs.dal.dao.Study;
import com.pacs.ui.beans.UserBean;
import com.pacs.ui.beans.admin.CriteriaBean;
import com.pacs.utils.HibernateUtilsAnnot;
import com.pacs.utils.MessageConstants;
import com.pacs.utils.MessageUtils;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;

public class SearchBll 
{
	UserBean ub = (UserBean)FacesUtils.getManagedBean("userBean");
	private CriteriaBean crit = (CriteriaBean)FacesUtils.getManagedBean("crit");
	
	public SearchBll() 
	{
		// TODO Auto-generated constructor stub
		init();
	}

	private void init()
	{
		
	}
	
	public List<Patient> searchPatients(Patient toSearchPatient, Date dateFrom , Date dateTo)
	{
		Session session = null;
		List<Patient> list = new ArrayList<Patient>();
		System.out.println("In search client Method bll");
		try
		{
			session = HibernateUtilsAnnot.currentSession();			
			Criteria cr = session.createCriteria(Patient.class);
			if(toSearchPatient!=null)
			{
				if(toSearchPatient.getId()!=null && toSearchPatient.getId()>0)
				{
					cr.add(Restrictions.eq("id", toSearchPatient.getId()));
				}
				if(toSearchPatient.getPatId()!=null && toSearchPatient.getPatId().trim().length()>0)
				{
					cr.add(Restrictions.ilike("patId", toSearchPatient.getPatId()));
				}
				if(toSearchPatient.getPatName()!=null && toSearchPatient.getPatName().trim().length()>0)
				{
					cr.add(Restrictions.ilike("patName", toSearchPatient.getPatName(),MatchMode.ANYWHERE));
				}
				if(toSearchPatient.getPatIdIssuer()!=null && toSearchPatient.getPatIdIssuer().trim().length()>0)
				{
					cr.add(Restrictions.ilike("patIdIssuer", toSearchPatient.getPatIdIssuer()));
				}
//				cr.add(Restrictions.between("eventTime", dateFrom, dateTo));
			}
//			cr.addOrder(Order.asc("eventTime"));
			list = cr.list();
			for(Patient c:list)
			{
				Hibernate.initialize(c.getStudiesFk());				
				for(Study s:c.getStudiesFk())
				{
					Hibernate.initialize(s.getSeriesFk());
				}
			}
			

			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		
		return list;
	}
	
	
	public List<Study> searchStudy(Patient toSearchPatient, Study toSearchStudy,
			Date dateFrom , Date dateTo, String isStudyDeletedOption)
	{
		Session session = null;
		List<Study> list = new ArrayList<Study>();
		list.clear();
		List<String> aetList = ub.getAetRoles();
		List<String> modList = ub.getModRoles();
		
		
		/*
		 * Following code clears roles list and displays all records to user if roles contain "All" role 
		 */
		if(aetList.contains(MessageConstants.Constants.ALL_STRING))
		{
			aetList.clear();
		}
		if(modList.contains(MessageConstants.Constants.ALL_STRING))
		{
			modList.clear();
		}
//		**********
		
		System.out.println("In search study Method bll");
		try
		{
			
			
			session = HibernateUtilsAnnot.currentSession();			
			Criteria mainCr = session.createCriteria(Study.class);
			mainCr.add(Restrictions.eq("isDelete", isStudyDeletedOption));
			if(dateFrom!=null)
			{
				mainCr.add(Restrictions.ge("studyDateTime", dateFrom));
			}
			if(dateTo!=null)
			{
				mainCr.add(Restrictions.le("studyDateTime", dateTo));
			}
			
			/*
			 * Following is for restricting results as per roles.
			 */
			if(aetList.size()>0 || modList.size()>0)
			{
				Criteria seriesCr = mainCr.createCriteria("seriesFk");
				if(aetList.size()>0 && modList.size()>0)
				{
					System.out.println("in aetlist and modlist");
					seriesCr.add(Restrictions.in("srcAet", aetList));
					seriesCr.add(Restrictions.in("modality", modList));
				}
				else if(aetList.size()>0)
				{
					System.out.println("in aetlist only");
					seriesCr.add(Restrictions.in("srcAet", aetList));					
				}
				else if(modList.size()>0)
				{
					System.out.println("in modlist only");
					seriesCr.add(Restrictions.in("modality", modList));
				}
				
			}
			
								
			if(toSearchStudy!=null)
			{
				if(toSearchStudy.getStudyId()!=null && toSearchStudy.getStudyId().trim().length()>0)
				{
					mainCr.add(Restrictions.eq("studyId", toSearchStudy.getStudyId()));
				}
			}
			
			if(toSearchPatient!=null)
			{
				Criteria cr = mainCr.createCriteria("patientFk");
				if(toSearchPatient.getId()!=null && toSearchPatient.getId()>0)
				{
					cr.add(Restrictions.eq("id", toSearchPatient.getId()));
				}
				if(toSearchPatient.getPatId()!=null && toSearchPatient.getPatId().trim().length()>0)
				{
					cr.add(Restrictions.ilike("patId", toSearchPatient.getPatId()));
				}
				if(toSearchPatient.getPatName()!=null && toSearchPatient.getPatName().trim().length()>0)
				{
					cr.add(Restrictions.ilike("patName", toSearchPatient.getPatName(),MatchMode.ANYWHERE));
				}
				if(toSearchPatient.getPatIdIssuer()!=null && toSearchPatient.getPatIdIssuer().trim().length()>0)
				{
					cr.add(Restrictions.ilike("patIdIssuer", toSearchPatient.getPatIdIssuer()));
				}
//				cr.add(Restrictions.between("eventTime", dateFrom, dateTo));
			}
//			cr.addOrder(Order.asc("eventTime"));
			
			mainCr.addOrder(Order.desc("studyDateTime"));
			
			list = mainCr.list();
			for(Study c:list)
			{
				//Hibernate.initialize(c.getSeriesFk());	
				String name = c.getPatientFk().getPatName();
				System.out.println("Name before: "+name);
				name=name.replaceAll("[\\^]", "");
				name = WordUtils.capitalizeFully(name);
				
				System.out.println("Name after: "+name);
				c.getPatientFk().setPatName(name);
				name = c.getPatientFk().getPatId();
				
//				name=name.replaceAll("[\\^]", "");
				name = WordUtils.capitalizeFully(name);
				c.getPatientFk().setPatId(name);
				
				if(crit.isSyncStatusOption())
				{
					String status=calculateSyncStatus(session,c.getId());
					c.setSyncStatus(status);
				}
				
			}
			if(list.size()==0)
			{
				MessageUtils.info("No results found");
			}
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		
		return list;
	}
	
	

	private String calculateSyncStatus(Session session,Integer studyId)
			throws HibernateException, Exception
	{
		String onlineStorageDrive = crit.getOnlineStoragePath();
		Integer counterAll=0,counterHit=0,percent=0;
		
		Criteria cr = session.createCriteria(Files.class);
		ProjectionList proList=Projections.projectionList();
		proList.add(Projections.property("filePath"));
		cr.setProjection(proList);
		Criteria instanceCr=cr.createCriteria("instanceFk");
		Criteria seriesCr = instanceCr.createCriteria("seriesFk");
		Criteria studyCr = seriesCr.createCriteria("studyFk");
		studyCr.add(Restrictions.eq("id", studyId));
		
		List<String> filePathList = cr.list();
		for(String filePath:filePathList)
		{
			String path = onlineStorageDrive+"/"+ filePath;
			System.out.println("File path: "+path);
			Path p = Paths.get(path);
			counterAll++;
			boolean exists = java.nio.file.Files.exists(p);
			if (exists) 
			{
			    System.out.println("File exists!");
			    counterHit++;
			} 
			else
			{
				System.out.println("File does not exist!");
			}
		}
		
		if(counterAll > 0)
		{
			percent = counterHit/counterAll*100;
		}
		else
		{
			percent=0;
		}
		System.out.println("Percentage="+percent);
		
		
		return percent.toString();
	}
	
	
	public boolean deleteStudyData(Study selectedStudy)
	{
		Session session = null;
		Transaction tx = null;
		System.out.println("In deleteStudyData Method bll");
		try
		{
			
			
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			selectedStudy.setIsDelete(MessageConstants.Constants.YES_STRING);
			session.update(selectedStudy);
			tx.commit();
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		
		return true;
	}
	
	public boolean deletePermStudyData(List<Study> selectedStudies)
	{
		Session session = null;
		Transaction tx = null;
		System.out.println("In deleteStudyData Method bll");
		try
		{
			
			
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			for(Study selectedStudy:selectedStudies)
			{
				if(selectedStudy.isSelectedForAction())
				{
					
					session.delete(selectedStudy);
					
					Criteria fileCr = session.createCriteria(Files.class);
					Criteria instanceCr = fileCr.createCriteria("instanceFk");
					Criteria seriesCr = instanceCr.createCriteria("seriesFk");
					seriesCr.add(Restrictions.eq("studyFk.id", selectedStudy.getId()));
					List<Files> list = fileCr.list();
					if(list.size()>0)
					{
//						Following code delete physical files from storage path
						File file=null;
						for(Files f:list)
						{
							String fileSysDir = f.getFileSystemFk().getDirPath();
							System.out.println("File to be deleted is :"+f.getFilePath());
							file = new File(fileSysDir+"\\"+ f.getFilePath());
							file.delete();
							
						}
						
					}
					
					
				}
				
			}			
			
			tx.commit();
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		
		return true;
	}
	
	public boolean restoreStudyData(List<Study> selectedStudies)
	{
		Session session = null;
		Transaction tx = null;
		System.out.println("In restoreStudyData Method bll");
		try
		{
			
			
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			for(Study selectedStudy:selectedStudies)
			{
				if(selectedStudy.isSelectedForAction())
				{
					selectedStudy.setIsDelete(MessageConstants.Constants.NO_STRING);
					session.update(selectedStudy);
				}
				
			}			
			
			tx.commit();
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		
		return true;
	}
	
	
}
