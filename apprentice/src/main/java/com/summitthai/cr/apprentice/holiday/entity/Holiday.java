package com.summitthai.cr.apprentice.holiday.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TM_HOLIDAY")
@Data
public class Holiday implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "HOLIDAY_UUID")
	private String holidayID;

	@Column(name = "DATE")
	private String date;

	@Column(name = "MONTH")
	private String month;

	@Column(name = "YEAR")
	private String year;

	@Column(name = "HOLIDAY_NAME")
	private String holidayName;

	@Column(name = "EFFECTIVE_TYPE")
	private String effectiveType;

	@Column(name = "POS_GROUP")
	private String posGroup;

	@Column(name = "ACTIVE_FLAG")
	private String activeFlag;

	@Column(name = "EFFECTIVE_DEPARTMENT")
	private String effectiveDepartment;

	@Column(name = "DEPARTMENT")
	private String department;

}
