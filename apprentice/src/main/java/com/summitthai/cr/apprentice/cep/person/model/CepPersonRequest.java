package com.summitthai.cr.apprentice.cep.person.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.summitthai.cr.apprentice.cep.kid.model.CepKidRequest;
import com.summitthai.cr.apprentice.cep.spouse.model.CepSpouseRequest;
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
@XmlRootElement(name = "cepSpouseRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class CepPersonRequest {
	private static final long seriaVersionUID =1L;
	
	@XmlElement(name = "perUUID")
	private String perUUID;
	
	@XmlElement(name = "perType")
	private String perType;
	
	@XmlElement(name = "posNumId")
	private String posNumId;
	
	@XmlElement(name = "perID")
	private String perID;
	
	@XmlElement(name = "perIdenNum")
	private String perIdenNum;
	
	@XmlElement(name = "perTitleName")
	private String perTitleName;
	
	@XmlElement(name = "perFirstName")
	private String perFirstName;
	
	@XmlElement(name = "perLastName")
	private String perLastName;
	
	@XmlElement(name = "perPosWork")
	private String perPosWork;
	
	@XmlElement(name = "perDept")
	private String perDept;
	
	@XmlElement(name = "perEmail")
	private String perEmail;
	
	private CepTuitionRequest cepTuitionReq;
	
	private List<CepSpouseRequest> spouseList;
	private List<CepTuitionRequest> tuitionList;
	private List<CepKidRequest> kidList;
	
	private String perFullName;
}
