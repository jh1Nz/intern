package com.summitthai.cr.apprentice.jpa.xsp.project.model;

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
@XmlRootElement(name = "XspProjectRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class XspProjectRequest {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "projectId")
	private String id;
	
	@XmlElement(name = "projectCode")
	private String projectCode;
	
	@XmlElement(name = "projectName")
	private String projectName;
	
	@XmlElement(name = "startDate")
	private String startDate;
	
	@XmlElement(name = "endDate")
	private String endDate;
	
	@XmlElement(name = "days")
	private String days;
	
	@XmlElement(name = "descriptions")
	private String descriptions;
}
