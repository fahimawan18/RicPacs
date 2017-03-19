package com.pacs.bll.admin;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.dcm4chex.archive.tools.Pwd2Hash;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.text.StrongTextEncryptor;
import org.primefaces.model.DualListModel;

//import com.lowagie.text.pdf.PRStream;
import com.pacs.dal.dao.ApplicationUsers;
import com.pacs.dal.dao.RolesApplAet;
import com.pacs.dal.dao.RolesApplModality;
import com.pacs.utils.Environment;
import com.pacs.utils.HibernateUtilsAnnot;
import com.pacs.utils.MessageConstants;
import com.pacs.utils.MessageUtils;


public class AdminBll 
{
	
	
	public AdminBll() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public boolean addNewUser(ApplicationUsers toAddUser,List selectedRolesList,List selectedRolesList2)
	{
		Session session = null;
		Transaction tx = null;
		System.out.println("In add new User Method bll");
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			Criteria cr =session.createCriteria(ApplicationUsers.class);
			cr.add(Restrictions.eq("userId", toAddUser.getUserId()));
					
			if(cr.list().size()>0)
			{
				MessageUtils.error("Username already exists");
				return false;
			}
//			StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
//			textEncryptor.setPassword(MessageConstants.Constants.PASSWORD_KEY);
//			String myEncryptedText = textEncryptor.encrypt(Environment.getDefaultPassword());
//			toAddUser.setPassword(myEncryptedText);
			String hashedPassword = computeHashedPassword(Environment.getDefaultPassword());
			toAddUser.setPassword(hashedPassword);
			session.save(toAddUser);
			session.flush();
			saveRoles(toAddUser, selectedRolesList, session);
			saveModalityRoles(toAddUser, selectedRolesList2, session);
			
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
	
	public boolean updateRoles(ApplicationUsers selectedUser,DualListModel<String> aetList,DualListModel<String> modList)
	{
		Session session = null;
		Transaction tx = null;
		System.out.println("In update User Method bll");
		try
		{
			session = HibernateUtilsAnnot.currentSession();
			tx = session.beginTransaction();
			
			List<String> presentList = new ArrayList<String>();
			for(RolesApplAet s:selectedUser.getRolesAetFk())
			{
				presentList.add(s.getUserRoleAet());
			}
			
			List<String> selectedRolesList = new ArrayList<String>();
			for(String d:aetList.getTarget())
			{
				if(!presentList.contains(d))
				{
					
					selectedRolesList.add(d);
					
				}
			}
			saveRoles(selectedUser, selectedRolesList, session);
			
			presentList.clear();
			for(RolesApplModality s:selectedUser.getRolesModFk())
			{
				presentList.add(s.getUserRoleModality());
			}
			selectedRolesList = new ArrayList<String>();
			for(String d:modList.getTarget())
			{
				if(!presentList.contains(d))
				{
					
					selectedRolesList.add(d);
					
				}
			}
			saveModalityRoles(selectedUser, selectedRolesList, session);
			
			session.flush();
			
			deleteRoles(selectedUser, aetList.getSource(), session);
			deleteModalityRoles(selectedUser, modList.getSource(), session);
			
//			session.update(selectedUser);
			
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
				
				if(toSearchUser.getUserName()!=null && toSearchUser.getUserName().trim().length()>0)
				{
					cr.add(Restrictions.ilike("userName", toSearchUser.getUserName(),MatchMode.ANYWHERE));
				}
				
				if(toSearchUser.getAppt()!=null && toSearchUser.getAppt().trim().length()>0)
				{
					cr.add(Restrictions.ilike("appt", toSearchUser.getAppt(),MatchMode.ANYWHERE));
				}
				
				if(toSearchUser.getDept()!=null && toSearchUser.getDept().trim().length()>0)
				{
					cr.add(Restrictions.ilike("dept", toSearchUser.getDept(),MatchMode.ANYWHERE));
				}
				
				
			}
			list = cr.list();
			if(list.size()>0)
			{
				for(ApplicationUsers u:list)
				{
					Hibernate.initialize(u.getRolesAetFk());
					Hibernate.initialize(u.getRolesModFk());
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
	
	private void saveRoles(ApplicationUsers user, List<String> selectedRolesList, Session session)
	throws HibernateException
	{
		for(String s:selectedRolesList)
		{
			RolesApplAet ra=new RolesApplAet();
			ra.setUserId(user);
			ra.setUserRoleAet(s);
			session.save(ra);
		}
		
	}
	
	private void deleteRoles(ApplicationUsers user, List<String> unselectedRolesList, Session session)
			throws HibernateException
	{
		if(unselectedRolesList.size()>0)
		{
			Criteria cr = session.createCriteria(RolesApplAet.class);
			cr.add(Restrictions.eq("userId.userId", user.getUserId()));
			cr.add(Restrictions.in("userRoleAet", unselectedRolesList));
			
			List<RolesApplAet> list = cr.list();
			for(RolesApplAet r:list)
			{
				session.delete(r);
			}
		}
		
	}
			
	
	private void saveModalityRoles(ApplicationUsers user, List<String> selectedRolesList, Session session)
			throws HibernateException
			{
				for(String s:selectedRolesList)
				{
					RolesApplModality ra = new RolesApplModality();
					ra.setUserId(user);
					ra.setUserRoleModality(s);
					session.save(ra);
				}
				
			}
	
	private void deleteModalityRoles(ApplicationUsers user, List<String> unselectedRolesList, Session session)
			throws HibernateException
	{
		if(unselectedRolesList.size()>0)
		{
			Criteria cr = session.createCriteria(RolesApplModality.class);
			cr.add(Restrictions.eq("userId.userId", user.getUserId()));
			cr.add(Restrictions.in("userRoleModality", unselectedRolesList));
			
			List<RolesApplModality> list = cr.list();
			for(RolesApplModality r:list)
			{
				session.delete(r);
			}
		}
		
	}

}
