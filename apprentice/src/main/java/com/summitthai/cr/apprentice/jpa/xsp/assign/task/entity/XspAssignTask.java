package com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "xsp_Assign_Task")
@Data
public class XspAssignTask implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "TASK_ID")
	private String id;
	
	@Column(name = "ACTOR_ID")
	private String actorId;
	
	@Column(name = "SCREEN_ID")
	private String screenId;
	
	@Column(name = "ACTOR_ROLE")
	private String actorRole;
	
	@Column(name = "ASSIGN_BY_ID")
	private String assignById;
	
	@Column(name = "ASSIGN_DATETIME")
	private String assignDatetime;
	
	@Column(name = "PLAN_START_DATE")
	private String planStartDate;
	
	@Column(name = "PLAN_END_DATE")
	private String planEndDate;
	
	@Column(name = "PLAN_DAYS")
	private int planDays;
	
	@Column(name = "ACTUAL_START_DATE")
	private String actualStartDate;
	
	@Column(name = "ACTUAL_END_DATE")
	private String actualEndDate;
	
	@Column(name = "ACTUAL_DAYS")
	private int actualDays;
	
	@Column(name = "TASK_DESCRIPTION")
	private String taskDescription;
	
	@Column(name = "TASK_LINK")
	private String taskLink;
	
	@Column(name = "TASK_STATUS")
	private String taskStatus;
	
	@Column(name = "SEND_TO_PERSON")
	private String sendToPerson;
	
	@Column(name = "ACTOR_REMARK")
	private String actorRemark;
	
	@Column(name = "SEND_TO_DATETIME")
	private String sendToDatetime;
	
	@Column(name = "WORK_HRS")
	private BigDecimal workHrs;
	
	@Column(name = "PROJECT_ID")
	private String projectId;
	
	@Column(name = "VERSION_ID")
	private String versionId;
	
	@Column(name = "CREATE_DATETIME")
	private Timestamp createDateTime;
	
	@Column(name = "CREATE_USER")
	private String createUser;
	
	@Column(name = "UPD_DATETIME")
	private Timestamp updDateTime;
	
	@Column(name = "UPD_USER")
	private String updUser;

}
