package com.summitthai.cr.apprentice.holiday.model;

import java.util.List;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.summitthai.cr.apprentice.deptH.model.HolidayDeptRequest;

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
@XmlRootElement(name ="holidayRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class HolidayRequest {
	private static final long seriaVersionUID =1L;
	
	@XmlElement(name = "holidayID")
	private String holidayID;

	@XmlElement(name = "date")
	private String date;

	@XmlElement(name = "month")
	private String month;

	@XmlElement(name = "year")
	private String year;

	@XmlElement(name = "holidayName")
	private String holidayName;

	@XmlElement(name = "effectiveType")
	private String effectiveType;

	@XmlElement(name = "posGroup")
	private String posGroup;

	@XmlElement(name = "activeFlag")
	private String activeFlag;
	
	@Column(name = "resultDept")
	private String resultDept;

	private List<HolidayDeptRequest> deptList;
	
}
