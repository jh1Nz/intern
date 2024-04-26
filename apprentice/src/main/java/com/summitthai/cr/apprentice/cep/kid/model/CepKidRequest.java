package com.summitthai.cr.apprentice.cep.kid.model;

import java.util.Date;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.summitthai.cr.apprentice.cep.tuition.model.CepTuitionRequest;

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
@XmlRootElement(name = "cepKidRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class CepKidRequest {
	private static final long seriaVersionUID =1L;
	
	@XmlElement(name = "kidUUID")
	private String kidUUID;
	
	@XmlElement(name = "perUUID")
	private String perUUID;
	
	@XmlElement(name = "kidMom")
	private String kidMom;
	
	@XmlElement(name = "kidDad")
	private String kidDad;
	
	@XmlElement(name = "kidName")
	private String kidName;
	
	@XmlElement(name = "kidAge")
	private String kidAge;
	
	@XmlElement(name = "eduClass")
	private String eduClass;
	
	@XmlElement(name = "eduLevel")
	private String eduLevel;
	
	@XmlElement(name = "eduName")
	private String eduName;
	
	@XmlElement(name = "disburType")
	private String disburType;
	
	@XmlElement(name = "eduAmount")
	private String eduAmount;
}
