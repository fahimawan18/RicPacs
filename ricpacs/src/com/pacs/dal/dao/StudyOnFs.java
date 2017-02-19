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
@Table(name = "study_on_fs")
public class StudyOnFs 
{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id;
	
	@ManyToOne 
	@JoinColumn (name = "study_fk")
	private Study studyFk;
	
	@ManyToOne 
	@JoinColumn (name = "filesystem_fk")
	private FileSystem fileSystemFk;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="access_time")
	private Date accessTime;
	
	@Column(name = "mark_to_delete", columnDefinition="BIT")
	private Boolean markToDelete;
	
	public StudyOnFs() 
	{
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Study getStudyFk() {
		return studyFk;
	}

	public void setStudyFk(Study studyFk) {
		this.studyFk = studyFk;
	}

	public FileSystem getFileSystemFk() {
		return fileSystemFk;
	}

	public void setFileSystemFk(FileSystem fileSystemFk) {
		this.fileSystemFk = fileSystemFk;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public Boolean getMarkToDelete() {
		return markToDelete;
	}

	public void setMarkToDelete(Boolean markToDelete) {
		this.markToDelete = markToDelete;
	}

}
