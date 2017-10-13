package com.pacs.dal.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lu_modality_alias")
public class LuModalityAlias 
{
	
	@Id 	
	@Column(name = "modality", nullable= false)
	private String modality;
	
	@Column(name = "modality_alias")
	private String modalityAlias;
	
	public LuModalityAlias() 
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
	
	

}
