package com.pacs.dal.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "issuer")
public class Issuer 
{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id;
	
	@Column(name="entity_id")
	private String entityId;
	
	@Column(name="entity_uid")
	private String entityUid;
	
	@Column(name="entity_uid_type")
	private String entityUidType;

	public Issuer() 
	{
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getEntityUid() {
		return entityUid;
	}

	public void setEntityUid(String entityUid) {
		this.entityUid = entityUid;
	}

	public String getEntityUidType() {
		return entityUidType;
	}

	public void setEntityUidType(String entityUidType) {
		this.entityUidType = entityUidType;
	}
}
