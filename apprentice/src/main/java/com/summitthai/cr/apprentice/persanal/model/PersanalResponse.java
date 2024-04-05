package com.summitthai.cr.apprentice.persanal.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement(name = "persanalResponse")
@XmlAccessorType(XmlAccessType.FIELD)

public class PersanalResponse {
	
	@XmlElement(name = "status")
	private String status;
	
	@XmlElement(name = "dataRequestList")
	private List<PersanalRequest> dataRequestList;
	
	@XmlElement(name = "error")
	private String error;

}
