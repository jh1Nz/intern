package com.summitthai.cr.apprentice.deptH.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TM_HOLIDAY_DEPT")
@Data
public class HolidayDept implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "HOLIDAY_DEPT_UUID")
	private String holidayDeptID;
	
	@Column(name = "HOLIDAY_UUID")
	private String holidayID;
	
	@Column(name = "HOLIDAY_DEPT")
	private String holidayDept;
}
