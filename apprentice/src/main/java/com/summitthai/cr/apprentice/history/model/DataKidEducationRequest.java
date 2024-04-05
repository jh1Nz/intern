package com.summitthai.cr.apprentice.history.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@EqualsAndHashCode(
		)
@ToString(
		callSuper = true,
		includeFieldNames = true,
		doNotUseGetters = false
		)
@XmlRootElement(name = "datakidRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class DataKidEducationRequest {
	private static final long seriaVersionUID =1L;
	
	@Id
	@XmlElement(name = "educationHistoryId")
	private String educationHistoryId;
	
	@XmlElement(name = "persanalInfoId")
	private String persanalInfoId;
	
	@XmlElement(name = "kidIname")
	private String kidIname;
	
	@XmlElement(name = "kidFname")
	private String kidFname;
	
	@XmlElement(name = "kidLname")
	private String kidLname;
	
	@XmlElement(name = "kidFullname")
	private String kidFullname;
	
	
	
	@XmlElement(name = "eduYear")
	private String eduYear;
	
	@XmlElement(name = "eduLevel")
	private String eduLevel;
	
	@XmlElement(name = "eduGran")
	private String eduGran;
	
	@XmlElement(name = "eduBranch")
	private String eduBranch;
	
	@XmlElement(name = "eduPlace")
	private String eduPlace;
	
	@XmlElement(name = "noTable")
	private int noTable;
	
	@XmlElement(name = "eduPlacePart")
	private String eduPlacePart;
	
	@XmlElement(name = "eduPlaceType")
	private String eduPlaceType;
	
	@XmlElement(name = "eduMark")
	private String eduMark;
	
	@XmlElement(name = "eduSusdistrict")
	private String eduSusdistrict;
	
	@XmlElement(name = "eduDistrict")
	private String eduDistrict;
	
	@XmlElement(name = "eduProvine")
	private String eduProvine;
	
	@XmlElement(name = "eduStart")
	private Date eduStart;
	

	
	
	
	
	
}
