package com.summitthai.cr.apprentice.cep.tuition.model;

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
@EqualsAndHashCode()
@ToString(
		callSuper = true,
		includeFieldNames = true,
		doNotUseGetters = false
		)
@XmlRootElement(name = "cepTuitionResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class CepTuitionResponse {
	@XmlElement(name = "id")
	private String id;
	
	@XmlElement(name = "status")
	private String status;
	
	@XmlElement(name = "dataRequestList")
	private List<CepTuitionRequest> dataRequestList;
	
	@XmlElement(name = "error")
	private String error;
}
