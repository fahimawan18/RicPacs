package com.pacs.bll.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.pacs.dal.dao.Patient;
import com.pacs.dal.dao.Study;
import com.pacs.utils.HibernateUtilsAnnot;

public class SearchBll 
{
	
	public SearchBll() 
	{
		// TODO Auto-generated constructor stub
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
