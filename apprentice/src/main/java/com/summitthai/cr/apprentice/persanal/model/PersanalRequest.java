package com.summitthai.cr.apprentice.persanal.model;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.summitthai.cr.apprentice.history.model.DataKidEducationRequest;
import com.summitthai.cr.apprentice.kids.model.DataKidRequest;

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
public class PersanalRequest {
	@XmlElement(name = "empType")
	private String empType;
	
	@XmlElement(name = "posNum")
	private String posNum;
	
	@XmlElement(name = "empId")
	private String empId;
	
	@XmlElement(name = "perId")
	private String perId;
	
	@XmlElement(name = "nameTitle")
	private String nameTitle;
	
	@XmlElement(name = "firstName")
	private String firstName;
	
	@XmlElement(name = "lastName")
	private String lastName;
	
	@XmlElement(name = "posJob")
	private String posJob;
	
	@XmlElement(name = "depastment")
	private String depastment;
	
	@XmlElement(name = "persanalInfoId")
	private String persanalInfoId;
	
	private List<DataKidRequest> kidslist;
	private List<DataKidEducationRequest> educationList;
	 
}
