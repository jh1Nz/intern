package com.summitthai.cr.apprentice.person.model;

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
@XmlRootElement(name = "personRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonRequest {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "empID")
	private String empID;

	@XmlElement(name = "empType")
	private String empType;

	@XmlElement(name = "empPosID")
	private String empPosID;

	@XmlElement(name = "identityNum")
	private String identityNum;

	@XmlElement(name = "initialName")
	private String initialName;

	@XmlElement(name = "firstName")
	private String firstName;

	@XmlElement(name = "lastName")
	private String lastName;

	@XmlElement(name = "posWork")
	private String posWork;

	@XmlElement(name = "department")
	private String department;

	@XmlElement(name = "perID")
	private String perID;
	
}
