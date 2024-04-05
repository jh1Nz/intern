package com.summitthai.cr.apprentice.jpa.xsp.assign.task.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;
import com.summitthai.cr.apprentice.jpa.xsp.project.model.XspProjectRequest;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenRequest;
import com.summitthai.cr.apprentice.utils.XspUtils;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@EqualsAndHashCode(
//		callSuper = true,
//		doNotUseGetters = false,
//		of = {"id"}
		)
@ToString(
		callSuper = true,
		includeFieldNames = true,
		doNotUseGetters = false
		)
@XmlRootElement(name = "XspAssignTaskRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class XspAssignTaskRequest {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "taskId")
	private String id;
	
	@XmlElement(name = "actorId")
	private String actorId;
	
	@XmlElement(name = "screenId")
	private String screenId;
	
	@XmlElement(name = "actorRole")
	private String actorRole;
	
	@XmlElement(name = "assignById")
	private String assignById;
	
	@XmlElement(name = "assignDatetime")
	private String assignDatetime;
	
	@XmlElement(name = "planStartDate")
	private String planStartDate;
	
	@XmlElement(name = "planEndDate")
	private String planEndDate;
	
	@XmlElement(name = "planDays")
	private int planDays;
	
	@XmlElement(name = "actualStartDate")
	private String actualStartDate;
	
	@XmlElement(name = "actualEndDate")
	private String actualEndDate;
	
	@XmlElement(name = "actualDays")
	private int actualDays;
	
	@XmlElement(name = "taskDescription")
	private String taskDescription;
	
	@XmlElement(name = "taskLink")
	private String taskLink;
	
	@XmlElement(name = "taskStatus")
	private String taskStatus;
	
	@XmlElement(name = "sendToPerson")
	private String sendToPerson;
	
	@XmlElement(name = "actorRemark")
	private String actorRemark;
	
	@XmlElement(name = "sendToDatetime")
	private String sendToDatetime;
	
	@XmlElement(name = "workHrs")
	private BigDecimal workHrs;
	
	@XmlElement(name = "projectId")
	private String projectId;
	
	@XmlElement(name = "versionId")
	private String versionId;
	
	@XmlElement(name = "createDateTime")
	private Timestamp createDateTime;
	
	@XmlElement(name = "createUser")
	private String createUser;
	
	@XmlElement(name = "updDateTime")
	private Timestamp updDateTime;
	
	@XmlElement(name = "updUser")
	private String updUser;
	
	// Use for FormCriteria
	private Date stDateUi; //วันที่เริ่มต้น
	
	private Date fnDateUi; //วันที่สิ้นสุด
	
	private String stDate;
	
	private String fnDate;
	// Use for FormCriteria
	
	// Use for FormEdit
	private Date stPlanDateUi; // แผน-วันที่เริ่ม
	
	private Date fnPlanDateUi; // แผน-วันที่สิ้นสุด
	
	private Date stActualDateUi; // ทำจริง-วันที่เริ่ม
	
	private Date fnActualDateUi; // ทำจริง-วันที่สิ้นสุด
	
	private Date sendToDatetimeUi;
	
	private Date actualStartDateUi;
	
	// Use for FormEdit
	
	private Date assignDatetimeUi; 
	
	private XspPersonRequest xspPersonAssignRequest;
	
	private XspPersonRequest xspPersonActorRequest;
	
	private XspPersonRequest xspPersonForwardRequest;
	
	private XspScreenRequest xspScreenNameRequest;
	
	private XspProjectRequest xspProjectRequest;
	
	
	private List<String> taskStatusList;
	
	public void setDateToString() {
		if (this.getAssignDatetimeUi() != null) {
			this.setAssignDatetime(XspUtils.convertDateToString(this.assignDatetimeUi));
		}
     // Use for FormCriteria
		if (this.getStDateUi() != null) {
			this.setStDate(XspUtils.convertDateToString(this.stDateUi));
		}
		if (this.getFnDateUi() != null) {
			this.setFnDate(XspUtils.convertDateToString(this.fnDateUi));
		}
     // Use for FormCriteria
        
     // Use for FormEdit
        if (this.getStPlanDateUi() != null) {
        	this.setPlanStartDate(XspUtils.convertDateToString(this.stPlanDateUi));
		}
        if (this.getFnPlanDateUi() != null) {
        	this.setPlanEndDate(XspUtils.convertDateToString(this.fnActualDateUi));
        }
        if (this.getStActualDateUi() != null) {
        	this.setActualStartDate(XspUtils.convertDateToString(this.stActualDateUi));
        }
        if (this.getFnActualDateUi() != null) {
        	this.setActualEndDate(XspUtils.convertDateToString(this.fnActualDateUi));
        }
        if (this.getSendToDatetimeUi() != null) {
			this.setSendToDatetime(XspUtils.convertDateToString(this.sendToDatetimeUi));
		}
        if (this.getActualStartDateUi() != null) {
			this.setActualStartDate(XspUtils.convertDateToString(this.actualStartDateUi));
		}
     // Use for FormEdit
    }

    public void setStringToDate() {
        try {
        	if (this.getAssignDatetime() != null) {
        		this.setAssignDatetimeUi(XspUtils.convertStringToDate(this.assignDatetime));
        	}
         // Use for FormCriteria
        	if (this.getStDate() != null) {
        		this.setStDateUi(XspUtils.convertStringToDate(this.stDate));
        	}
        	if (this.getFnDate() != null) {
        		this.setFnDateUi(XspUtils.convertStringToDate(this.fnDate));
        	}
         // Use for FormCriteria
            
         // Use for FormEdit
        	if (this.getPlanStartDate() != null) {
        		this.setStPlanDateUi(XspUtils.convertStringToDate(this.planStartDate));
        	}
        	if (this.getPlanEndDate() != null) {
        		this.setFnPlanDateUi(XspUtils.convertStringToDate(this.planEndDate));
        	}
        	if (this.getActualStartDate() != null) {
        		this.setStActualDateUi(XspUtils.convertStringToDate(this.actualStartDate));
        	}
        	if (this.getActualEndDate() != null) {
        		this.setFnActualDateUi(XspUtils.convertStringToDate(this.actualEndDate));
        	}
        	if (this.getSendToDatetime() != null) {
				this.setSendToDatetimeUi(XspUtils.convertStringToDate(this.sendToDatetime));
			}
        	if (this.getActualStartDate() != null) {
				this.setActualStartDateUi(XspUtils.convertStringToDate(this.actualStartDate));
			}
         // Use for FormEdit
        } catch (Exception e) {
         log.error("Exception setStringToDate : {}", e);
        }
    }
	
}
