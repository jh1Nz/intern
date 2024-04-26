package com.summitthai.cr.apprentice.cep.spouse.model;

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
@EqualsAndHashCode()
@ToString(callSuper = true, includeFieldNames = true, doNotUseGetters = false)
@XmlRootElement(name = "cepPersonRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class CepSpouseRequest {
	private static final long seriaVersionUID = 1L;
	
	@XmlElement(name = "spouseUUID")
	private String spouseUUID;
	
	@XmlElement(name = "spouseTitleName")
	private String spouseTitleName;

	@XmlElement(name = "spouseFirstName")
	private String spouseFirstName;

	@XmlElement(name = "spouseLastName")
	private String spouseLastName;

	@XmlElement(name = "spouseRelation")
	private String spouseRelation;

	@XmlElement(name = "spouseStatus")
	private String spouseStatus;

	@XmlElement(name = "spouseJob")
	private String spouseJob;

	@XmlElement(name = "spouseOtherJob")
	private String spouseOtherJob;

	@XmlElement(name = "checkGovEmp")
	private String checkGovEmp;

	@XmlElement(name = "gov")
	private String gov;

	@XmlElement(name = "emp")
	private String emp;

	@XmlElement(name = "entGov")
	private String entGov;

	@XmlElement(name = "orgBkk")
	private String orgBkk;

	@XmlElement(name = "pos")
	private String pos;

	@XmlElement(name = "affiliationn")
	private String affiliationn;

	@XmlElement(name = "posSub")
	private String posSub;

	@XmlElement(name = "affiliationnSub")
	private String affiliationnSub;
	
	@XmlElement(name = "perUUID")
	private String perUUID;

}
