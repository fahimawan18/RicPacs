package com.pacs.dal.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roles_appl_aet")
public class RolesApplAet 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id; 
	
	@ManyToOne 
	@JoinColumn (name = "user_id")
	private ApplicationUsers userId;
	
	@Column(name="user_role_aet")
	private String userRoleAet;
	
	
	public RolesApplAet() 
	{
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public ApplicationUsers getUserId() {
		return userId;
	}


	public void setUserId(ApplicationUsers userId) {
		this.userId = userId;
	}


	public String getUserRoleAet() {
		return userRoleAet;
	}


	public void setUserRoleAet(String userRoleAet) {
		this.userRoleAet = userRoleAet;
	}

}
