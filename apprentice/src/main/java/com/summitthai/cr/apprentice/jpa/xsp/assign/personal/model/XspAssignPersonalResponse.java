package com.summitthai.cr.apprentice.jpa.xsp.assign.personal.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.jpa.rs.exceptions.ErrorResponse;

import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonResponse;

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
@XmlRootElement(name = "XspAssignTaskResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class XspAssignPersonalResponse {
	
	@XmlElement(name = "id")
	private String id;
	
	@XmlElement(name = "status")
	private String status;
	
	@XmlElement(name = "dataRequestList")
	private List<XspAssignPersanalRequest> dataRequestList;
	
	@XmlElement(name = "error")
	private String error;
}	
