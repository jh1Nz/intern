package com.summitthai.cr.apprentice.jpa.xsp.project.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskResponse;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@EqualsAndHashCode(
//		callSuper = true,
//		doNotUseGetters = false,
//		of = {"dataRequestListh"}
		)
@ToString(
		callSuper = true,
		includeFieldNames = true,
		doNotUseGetters = false
		)
@XmlRootElement(name = "XspProjectResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class XspProjectResponse {
	
	@XmlElement(name = "id")
	private String id;
	
	@XmlElement(name = "status")
	private String status;
	
	@XmlElement(name = "dataRequestList")
	private List<XspProjectRequest> dataRequestList;
	
	@XmlElement(name = "error")
	private String error;
}
