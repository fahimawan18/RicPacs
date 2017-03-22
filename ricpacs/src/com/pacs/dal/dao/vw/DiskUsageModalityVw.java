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
@Table(name = "disk_usage_modality_vw")
public class DiskUsageModalityVw 
{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "modality")
	private String modality;
	
	@Column(name="files_size")
	private Double fileSize;
	
	public DiskUsageModalityVw() 
	{
		// TODO Auto-generated constructor stub
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public Double getFileSize() {
		return fileSize;
	}

	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}

}
