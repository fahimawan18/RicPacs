package com.pacs.bll.admin;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.dcm4chex.archive.tools.Pwd2Hash;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.text.StrongTextEncryptor;

import com.pacs.dal.dao.ApplicationUsers;
import com.pacs.utils.Environment;
import com.pacs.utils.HibernateUtilsAnnot;
import com.pacs.utils.MessageConstants;


public class AdminBll 
{
	
	
	public AdminBll() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public boolean addNewUser(ApplicationUsers toAddUser)
	{
		Session session = null;
		Transaction tx = null;
		System.out.println("In add new User Method bll");
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
//			StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
//			textEncryptor.setPassword(MessageConstants.Constants.PASSWORD_KEY);
//			String myEncryptedText = textEncryptor.encrypt(Environment.getDefaultPassword());
//			toAddUser.setPassword(myEncryptedText);
			session.save(toAddUser);
			
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
	
	public boolean updateUsers(ApplicationUsers user)
	{
		Session session = null;
		Transaction tx = null;
		System.out.println("In update User Method bll" + user.getTheme());
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			session.update(user);
			
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
	public boolean updateUsers(List<ApplicationUsers> usersList)
	{
		Session session = null;
		Transaction tx = null;
		System.out.println("In update User Method bll");
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			for(ApplicationUsers u:usersList)
			{
				
				session.update(u);
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
	
	public List<ApplicationUsers> searchAllUser(ApplicationUsers toSearchUser)
	{
		Session session = null;
		List<ApplicationUsers> list = new ArrayList<ApplicationUsers>();
		System.out.println("In search User Method bll");
		try
		{
			session = HibernateUtilsAnnot.currentSession();			
			Criteria cr = session.createCriteria(ApplicationUsers.class);
			if(toSearchUser!=null)
			{
				if(toSearchUser.getUserId()!=null && toSearchUser.getUserId().trim().length()>0)
				{
					cr.add(Restrictions.eq("userId", toSearchUser.getUserId()));
				}
				
			}
			list = cr.list();
	
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
	
	public boolean changePassword(ApplicationUsers user,String newPassword)
	{
		Session session = null;
		Transaction tx = null;
		System.out.println("In change password Method bll");
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			String hashedPassword = computeHashedPassword(newPassword);
//			System.out.println("typed password ="+newPassword);
//			System.out.println("hashed password ="+hashedPassword);
			user.setPassword(hashedPassword);
			session.update(user);
			
			tx.commit();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			tx.rollback();
			return false;
		}
		catch(UnsupportedEncodingException e)
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
	
	private String computeHashedPassword(String userPassword)throws UnsupportedEncodingException, Exception
	{
		String hashedPassword="";
		byte[] hash = Pwd2Hash.createHash(userPassword);
		hashedPassword = javax.xml.bind.DatatypeConverter.printBase64Binary(hash);
		return hashedPassword;
	}
	

}
