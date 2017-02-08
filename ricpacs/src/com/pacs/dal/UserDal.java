package com.pacs.dal;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.pacs.dal.dao.ApplicationUsers;
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
	

}
