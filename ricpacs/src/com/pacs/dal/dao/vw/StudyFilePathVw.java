package com.pacs.dal.dao.vw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;
import com.pacs.dal.dao.Study;

@Entity
@Immutable
@Table(name = "study_filepath_vw")
public class StudyFilePathVw 
{
	@Id
	@Column(name = "filespath_in_db")
	private String filePath;
	
	
	@Column (name = "study_id")
	private Integer studyId;
	
	@ManyToOne
	@JoinColumn (name = "study_id", insertable=false, updatable=false)
	private Study studyObj;
	
	public StudyFilePathVw() 
	{
		// TODO Auto-generated constructor stub
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getStudyId() {
		return studyId;
	}

	public void setStudyId(Integer studyId) {
		this.studyId = studyId;
	}

	public Study getStudyObj() {
		return studyObj;
	}

	public void setStudyObj(Study studyObj) {
		this.studyObj = studyObj;
	}	
	

}
