package com.summitthai.cr.apprentice.jpa.xsp.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;

import lombok.Data;

@Entity
@Table(name = "xsp_Project")
@Data
public class XspProject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PROJECT_ID")
	private String id;
	
	@Column(name = "PROJECT_CODE")
	private String projectCode;
	
	@Column(name = "PROJECT_NAME")
	private String projectName;
	
	@Column(name = "START_DATE")
	private String startDate;
	
	@Column(name = "END_DATE")
	private String endDate;
	
	@Column(name = "DAYS")
	private String days;
	
	@Column(name = "DESCRIPTIONS")
	private String descriptions;
	
	
}
