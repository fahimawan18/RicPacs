package com.pacs.bll.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.poi.util.StringUtil;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.iac.util.StringUtils;
import com.iac.web.util.FacesUtils;
import com.pacs.dal.dao.Patient;
import com.pacs.dal.dao.Study;
import com.pacs.ui.beans.UserBean;
import com.pacs.utils.HibernateUtilsAnnot;
import com.pacs.utils.MessageConstants;
import com.pacs.utils.MessageUtils;
import com.sun.xml.internal.fastinfoset.algorithm.BuiltInEncodingAlgorithm.WordListener;

public class SearchBll 
{
	UserBean ub = (UserBean)FacesUtils.getManagedBean("userBean");
	
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
			Date dateFrom , Date dateTo)
	{
		Session session = null;
		List<Study> list = new ArrayList<Study>();
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
			list = mainCr.list();
			for(Study c:list)
			{
				Hibernate.initialize(c.getSeriesFk());	
				String name = c.getPatientFk().getPatName();
				System.out.println("Name before: "+name);
				name = WordUtils.capitalizeFully(name);
				name=name.replaceAll("[\\^]", "");
				System.out.println("Name after: "+name);
				c.getPatientFk().setPatName(name);
				
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
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		
		return list;
	}
	
	
}
