package com.pacs.dal.dao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class ApplicationUsers 
{
	
	@Id // @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id")
	private String userId;
	
	@Column(name="passwd")
	private String password;
	
	@Column(name="THEME", insertable=false)
	private String theme;
	
	@OneToMany(mappedBy = "userId" , cascade = CascadeType.ALL)
	private  List<RolesApplAet> rolesAetFk;
	
	@OneToMany(mappedBy = "userId" , cascade = CascadeType.ALL)
	private  List<RolesApplModality> rolesModFk;
	
	
	public ApplicationUsers() 
	{
		// TODO Auto-generated constructor stub
	}

	
	public String getLoggedUserString()
	{
		return this.userId;
	}
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getTheme() {
		return theme;
	}


	public void setTheme(String theme) {
		this.theme = theme;
	}


	public List<RolesApplAet> getRolesAetFk() {
		return rolesAetFk;
	}


	public void setRolesAetFk(List<RolesApplAet> rolesAetFk) {
		this.rolesAetFk = rolesAetFk;
	}


	public List<RolesApplModality> getRolesModFk() {
		return rolesModFk;
	}


	public void setRolesModFk(List<RolesApplModality> rolesModFk) {
		this.rolesModFk = rolesModFk;
	}
	

}
