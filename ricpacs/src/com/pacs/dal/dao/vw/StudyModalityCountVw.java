package com.pacs.dal.dao.vw;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "study_modality_count_vw")
public class StudyModalityCountVw 
{
	
	@Id
	@Column(name = "modality")
	private String modality;
	
	
	@Column(name = "modality_alias")
	private String modalityAlias;
	
	
	@Column(name = "study_count")
	private Integer studyCount;
	
	
	public StudyModalityCountVw() 
	{
		// TODO Auto-generated constructor stub
	}

	public String getModality() {
		return modality;
	}

	public void setModality(String modality) {
		this.modality = modality;
	}

	public String getModalityAlias() {
		return modalityAlias;
	}

	public void setModalityAlias(String modalityAlias) {
		this.modalityAlias = modalityAlias;
	}

	public Integer getStudyCount() {
		return studyCount;
	}

	public void setStudyCount(Integer studyCount) {
		this.studyCount = studyCount;
	}

}
