package com.summitthai.cr.apprentice.calculation.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@EqualsAndHashCode()
@ToString(
		callSuper = true,
		includeFieldNames = true,
		doNotUseGetters = false
		)
@XmlRootElement(name = "calculationRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculationRequest {
	
	private static final long serialVersionUID = 1L;
	private Integer numInt1; 
	private Integer numInt2;
	private Integer resultInt;
	private BigDecimal numBig1;
	private BigDecimal numBig2;
	private BigDecimal resultBig;
	private Integer Op;
	private Boolean checkBoolean;
	
}
