package com.pacs.dal.dao.vw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "lu_aet_vw")
public class LuAetVw 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "src_aet")
	private String srcAet; 
	
	public LuAetVw() 
	{
		// TODO Auto-generated constructor stub
	}

	public String getSrcAet() {
		return srcAet;
	}

	public void setSrcAet(String srcAet) {
		this.srcAet = srcAet;
	}
	
	

}
