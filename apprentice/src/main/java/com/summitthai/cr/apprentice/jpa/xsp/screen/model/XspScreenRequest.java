package com.summitthai.cr.apprentice.jpa.xsp.screen.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;

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
@XmlRootElement(name = "XspScreenRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class XspScreenRequest {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "screenId")
	private String id;
	
	@XmlElement(name = "screenCode")
	private String screenCode;
	
	@XmlElement(name = "screenName")
	private String screenName;
	
	@XmlElement(name = "screenType")
	private String screenType;
	
	@XmlElement(name = "screenLevel")
	private String screenLevel;
	
	@XmlElement(name = "mandays")
	private String mandays;
	
	@XmlElement(name = "descriptions")
	private String descriptions;
}
