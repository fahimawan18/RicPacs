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
@Table(name = "disk_usage_aet_vw")
public class DiskUsageAetVw 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "src_aet")
	private String srcAet;
	
	@Column(name="files_size")
	private Double fileSize;
	
	public DiskUsageAetVw() 
	{
		// TODO Auto-generated constructor stub
	}
	public Double getFileSize() {
		return fileSize;
	}

	public void setFileSize(Double fileSize) {
		this.fileSize = fileSize;
	}
	public String getSrcAet() {
		return srcAet;
	}
	public void setSrcAet(String srcAet) {
		this.srcAet = srcAet;
	}

}
