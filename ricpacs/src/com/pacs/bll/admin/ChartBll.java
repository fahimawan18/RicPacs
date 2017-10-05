package com.pacs.bll.admin;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.model.chart.PieChartModel;
import com.iac.web.util.FacesUtils;
import com.pacs.dal.dao.vw.DiskUsageAetVw;
import com.pacs.dal.dao.vw.DiskUsageModalityVw;
import com.pacs.ui.beans.admin.CriteriaBean;
import com.pacs.utils.HibernateUtilsAnnot;

public class ChartBll 
{
	
	private Long freeSpace = ((CriteriaBean)FacesUtils.getManagedBean("crit")).getOnlineStorageFreeSpace();
	
	public ChartBll() 
	{
		// TODO Auto-generated constructor stub
	}
	public PieChartModel populateAetChart(PieChartModel aetModel,int option,int freeSpaceShow)
	{
		//option> 1 -> mb, 2->gb
		Session session=null;
		try
		{
			List<DiskUsageAetVw> list = new ArrayList<DiskUsageAetVw>();
			session = HibernateUtilsAnnot.currentSession();
			Criteria cr = session.createCriteria(DiskUsageAetVw.class);
			list = cr.list();
			if(list.size()>0)
			{
				for(DiskUsageAetVw v:list)
				{
					Double s = v.getFileSize();
					s=convertBytes(s, option);
					aetModel.set(v.getSrcAet(), s);
				}
			}
			Double d =convertBytes(freeSpace.doubleValue(), option);
			if(freeSpaceShow==1)
			{
				aetModel.set("Available", d);
			}
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			HibernateUtilsAnnot.closeSession();
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		return aetModel;
	}
	
	public PieChartModel populateModalityChart(PieChartModel modalityModel,int option,int freeSpaceShow)
	{
		//option> 1 -> mb, 2->gb
		Session session=null;
		try
		{
			List<DiskUsageModalityVw> list = new ArrayList<DiskUsageModalityVw>();
			session = HibernateUtilsAnnot.currentSession();
			Criteria cr = session.createCriteria(DiskUsageModalityVw.class);
			list = cr.list();
			if(list.size()>0)
			{
				for(DiskUsageModalityVw v:list)
				{
					Double s = v.getFileSize();
					s=convertBytes(s, option);
					if(v.getModalityAlias()==null || v.getModalityAlias().trim().length()==0)
					{
						modalityModel.set(v.getModality(), s);
					}
					else
					{
						modalityModel.set(v.getModalityAlias(), s);
					}
				}
			}
			Double d =convertBytes(freeSpace.doubleValue(), option);
			if(freeSpaceShow==1)
			{
				modalityModel.set("Available", d);
			}
			
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
			HibernateUtilsAnnot.closeSession();
		}
		finally
		{
			HibernateUtilsAnnot.closeSession();
		}
		return modalityModel;
	}
	
	
	
	private Double convertBytes(Double bytes,int option)
	{
		Double d=0.0;
		if(option == 0)//KB
		{
			d= bytes/1024.00;
		}
		else if(option == 1)//MB
		{
			d= bytes/1024.00/1024.00;
		}
		else if(option == 2)//GB
		{
			d= bytes/1024.00/1024.00/1024.00;
		}
		return d;
	}
	
	
	
}

