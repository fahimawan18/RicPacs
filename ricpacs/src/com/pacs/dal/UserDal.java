package com.pacs.dal;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.mysql.fabric.xmlrpc.base.Array;
import com.pacs.dal.dao.ApplicationUsers;
import com.pacs.dal.dao.RolesApplAet;
import com.pacs.dal.dao.RolesApplModality;
import com.pacs.utils.HibernateUtilsAnnot;
public class UserDal 
{
	
	public UserDal() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public ApplicationUsers localLogin(String userName) throws Exception
	{
		ApplicationUsers obj = new ApplicationUsers();

		Session session = null;
		try {
			session = HibernateUtilsAnnot.currentSession();
			Criteria cr = session.createCriteria(ApplicationUsers.class);
			cr.add(Restrictions.ilike("userId", userName));
			obj = (ApplicationUsers)cr.uniqueResult();
		}

		catch (HibernateException e) 
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
			//return null;
		} finally {
			session.flush();
			HibernateUtilsAnnot.closeSession();
		}

		return obj;
	}
	
	public List<String> populateAetRoles(String userId)
	{
		List<String> list = new ArrayList<String>();
		
		Session session = null;
		try {
			session = HibernateUtilsAnnot.currentSession();
			Criteria cr = session.createCriteria(RolesApplAet.class);
			cr.add(Restrictions.ilike("userId.userId", userId));
			ProjectionList proList=Projections.projectionList();
			proList.add(Projections.property("userRoleAet"));
			cr.setProjection(proList);
			
			list = cr.list();
			
		}

		catch (HibernateException e) 
		{
			e.printStackTrace();
			
			//return null;
		} finally {
			session.flush();
			HibernateUtilsAnnot.closeSession();
		}
		
		
		return list;
		
		
	}
	
	public List<String> populateModRoles(String userId)
	{
		List<String> list = new ArrayList<String>();
		
		Session session = null;
		try {
			session = HibernateUtilsAnnot.currentSession();
			Criteria cr = session.createCriteria(RolesApplModality.class);
			cr.add(Restrictions.ilike("userId.userId", userId));
			ProjectionList proList=Projections.projectionList();
			proList.add(Projections.property("userRoleModality"));
			cr.setProjection(proList);
			
			list = cr.list();
		}

		catch (HibernateException e) 
		{
			e.printStackTrace();
			
			//return null;
		} finally {
			session.flush();
			HibernateUtilsAnnot.closeSession();
		}
		
		
		return list;
		
		
	}

}
