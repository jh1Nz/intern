package com.summitthai.cr.apprentice.jpa.xsp.person.model;

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
//		callSuper = true,
//		doNotUseGetters = false,
//		of = {"id"}
		)
@ToString(
		callSuper = true,
		includeFieldNames = true,
		doNotUseGetters = false
		)
@XmlRootElement(name = "xspPersonRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class XspPersonRequest {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "personId")
	private String id;
	
	@XmlElement(name = "personNickname")
	private String personNickname;
	
	@XmlElement(name = "personFullname")
	private String personFullname;
	
	@XmlElement(name = "personPosition")
	private String personPosition;
	
	@XmlElement(name = "descriptions")
	private String descriptions;
}
