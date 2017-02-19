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
@Table(name = "filesystem")
public class FileSystem 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id;
	
	@ManyToOne 
	@JoinColumn (name = "next_fk")
	private FileSystem nextFk;
	
	@Column(name="dirpath")
	private String dirPath;
	
	@Column(name="fs_group_id")
	private String fsGroupId;
	
	@Column(name="retrieve_aet")
	private String retrieveAet;
	
	@Column(name="availability")
	private Integer availability;
	
	@Column(name="fs_status")
	private Integer fsStatus;
	
	@Column(name="userInfo")
	private String userInfo;
	
	public FileSystem() 
	{
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}

	public String getFsGroupId() {
		return fsGroupId;
	}

	public void setFsGroupId(String fsGroupId) {
		this.fsGroupId = fsGroupId;
	}

	public String getRetrieveAet() {
		return retrieveAet;
	}

	public void setRetrieveAet(String retrieveAet) {
		this.retrieveAet = retrieveAet;
	}

	public Integer getAvailability() {
		return availability;
	}

	public void setAvailability(Integer availability) {
		this.availability = availability;
	}

	public Integer getFsStatus() {
		return fsStatus;
	}

	public void setFsStatus(Integer fsStatus) {
		this.fsStatus = fsStatus;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public FileSystem getNextFk() {
		return nextFk;
	}

	public void setNextFk(FileSystem nextFk) {
		this.nextFk = nextFk;
	}

}
