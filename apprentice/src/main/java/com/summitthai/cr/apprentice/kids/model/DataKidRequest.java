package com.summitthai.cr.apprentice.kids.model;

import java.util.Date;

import javax.persistence.Column;
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
public class DataKidRequest {
	private static final long seriaVersionUID =1L;
		
	@XmlElement(name = "kidInfoId")
	private String kidInfoId;
	
	@XmlElement(name = "kidType")
	private String kidType;
	
	
	@XmlElement(name = "kidSex")
	private String kidSex;
	
	@XmlElement(name = "kidIname")
	private String kidIname;
	
	@XmlElement(name = "kidFname")
	private String kidFname;
	
	@XmlElement(name = "kidLname")
	private String kidLname;
	
	
	@XmlElement(name = "persanalInfoId")
	private String persanalInfoId;
	
	
	@XmlElement(name = "kidBirtday")
	private Date kidBirtday;
	
	@XmlElement(name = "kidMom")
	private String kidMom;
	
	@XmlElement(name = "kidDad")
	private String kidDad;
	
	@XmlElement(name = "kidCheckStatus")
	private String kidCheckStatus;
	
	@XmlElement(name = "kidDateDie")
	private Date kidDateDie;
	
	@XmlElement(name = "kid2CheckStatus")
	private String kid2CheckStatus;
	
	@XmlElement(name = "kidTitle")
	private String kidTitle;
	
	@XmlElement(name = "kidFirstNameDie")
	private String kidFirstNameDie;
	
	@XmlElement(name = "kidLastNameDie")
	private String kidLastNameDie;
	
	
	@XmlElement(name = "kidChangeBirt")
	private Date kidChangeBirt;
	
	@XmlElement(name = "kidPersanalId")
	private String kidPersanalId;
	
	@XmlElement(name = "kidAge")
	private Integer kidAge;
	
		
		private String kidFullName;
	
	
		
}
