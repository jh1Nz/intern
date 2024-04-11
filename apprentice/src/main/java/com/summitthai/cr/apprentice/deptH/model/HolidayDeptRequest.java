package com.summitthai.cr.apprentice.deptH.model;

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
@EqualsAndHashCode()
@ToString(
		callSuper = true,
		includeFieldNames = true,
		doNotUseGetters = false
		)
@XmlRootElement(name ="deptHRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class HolidayDeptRequest {
	private static final long seriaVersionUID =1L;
	
	@XmlElement(name = "HOLIDAY_DEPT_UUID")
	private String holidayDeptID;
	
	@XmlElement(name = "HOLIDAY_UUID")
	private String holidayID;
	
	
	@XmlElement(name = "HOLIDAY_DEPT")
	private String holidayDept;
}
