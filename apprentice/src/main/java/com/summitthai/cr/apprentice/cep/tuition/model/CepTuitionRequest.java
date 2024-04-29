package com.summitthai.cr.apprentice.cep.tuition.model;

import java.util.Date;

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
@ToString(
		callSuper = true,
		includeFieldNames = true,
		doNotUseGetters = false
		)
@XmlRootElement(name = "cepTuitionRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class CepTuitionRequest {
	private static final long seriaVersionUID =1L;
	
	@XmlElement(name = "tuitionUUID")
	private String tuitionUUID;
	
	@XmlElement(name = "reqDate")
	private Date reqDate;
	
	@XmlElement(name = "reqID")
	private String reqID;
	
	@XmlElement(name = "reqStatus")
	private String reqStatus;
	
	@XmlElement(name = "reqClaim")
	private String reqClaim;
	
	@XmlElement(name = "eduYear")
	private String eduYear;
	
	@XmlElement(name = "semester")
	private String semester;
	
	@XmlElement(name = "welfarePick")
	private String welfarePick;
	
	@XmlElement(name = "welfareAmount")
	private String welfareAmount;
	
	@XmlElement(name = "guaranteeAll")
	private String guaranteeAll;
	
	@XmlElement(name = "guaranteeKid")
	private String guaranteeKid;
	
	@XmlElement(name = "guranteePer")
	private String guranteePer;
	
	@XmlElement(name = "guranteeSpouse")
	private String guranteeSpouse;
	
	@XmlElement(name = "guranteeAmount")
	private String guranteeAmount;
	
	@XmlElement(name = "perUUID")
	private String perUUID;
	
	private Date reqStart;
	private Date reqEnd;
	
}
