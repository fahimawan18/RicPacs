package com.pacs.dal.dao.priv;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "priv_series")
public class PrivSeries 
{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "pk")
	private Integer id;
	
	@ManyToOne 
	@JoinColumn (name = "study_fk")
	private PrivStudy studyFk;

	@Column(name="priv_type")
	private Integer privType;
	
	@Column(name="series_iuid")
	private String seriesIuid;
	
	@Column(name="src_aet")
	private String srcAet;
	
	@Column(name= "series_attrs")
	private byte[] seriesAttrs;
	
	public PrivSeries() 
	{
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPrivType() {
		return privType;
	}

	public void setPrivType(Integer privType) {
		this.privType = privType;
	}

	public String getSeriesIuid() {
		return seriesIuid;
	}

	public void setSeriesIuid(String seriesIuid) {
		this.seriesIuid = seriesIuid;
	}

	public String getSrcAet() {
		return srcAet;
	}

	public void setSrcAet(String srcAet) {
		this.srcAet = srcAet;
	}

	public byte[] getSeriesAttrs() {
		return seriesAttrs;
	}

	public void setSeriesAttrs(byte[] seriesAttrs) {
		this.seriesAttrs = seriesAttrs;
	}

	public PrivStudy getStudyFk() {
		return studyFk;
	}

	public void setStudyFk(PrivStudy studyFk) {
		this.studyFk = studyFk;
	}
	
	

}
