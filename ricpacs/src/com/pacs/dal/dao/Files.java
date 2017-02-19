package com.pacs.dal.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "files")
public class Files 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id;
	
//	@Column(name="instance_fk")
//	private Instance instanceFk;
	
	@ManyToOne 
	@JoinColumn (name = "filesystem_fk")
	private FileSystem fileSystemFk;
	
	@Column(name="filepath")
	private String filePath;
	
	@Column(name="file_tsuid")
	private String fileTsuid;
	
	@Column(name="file_md5")
	private String fileMd5;
	
	@Column(name="file_size")
	private Integer fileSize;
	
	@Column(name="file_status")
	private Integer fileStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="md5_check_time")
	private Date md5CheckTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="cretaed_time")
	private Date createdTime;
	
	public Files() 
	{
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileTsuid() {
		return fileTsuid;
	}

	public void setFileTsuid(String fileTsuid) {
		this.fileTsuid = fileTsuid;
	}

	public String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(Integer fileStatus) {
		this.fileStatus = fileStatus;
	}

	public Date getMd5CheckTime() {
		return md5CheckTime;
	}

	public void setMd5CheckTime(Date md5CheckTime) {
		this.md5CheckTime = md5CheckTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public FileSystem getFileSystemFk() {
		return fileSystemFk;
	}

	public void setFileSystemFk(FileSystem fileSystemFk) {
		this.fileSystemFk = fileSystemFk;
	}

}
