package com.summitthai.cr.apprentice.jpa.xsp.assign.task.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.person.entity.XspPerson;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString
public class XspAssignTaskDto implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	 public XspAssignTaskRequest entityToReq(XspAssignTask entity) {
		 	XspAssignTaskRequest req = null;

	   try {
	       req = XspAssignTaskRequest.builder().build();
	       req.setId				(entity.getId());
	       req.setActorId			(entity.getActorId());
	       req.setScreenId			(entity.getScreenId());
	       req.setActorRole			(entity.getActorRole());
	       req.setAssignById		(entity.getAssignById());
	       req.setAssignDatetime	(entity.getAssignDatetime());
	       req.setPlanStartDate		(entity.getPlanStartDate());
	       req.setPlanEndDate		(entity.getPlanEndDate());
	       req.setPlanDays			(entity.getPlanDays());
	       req.setActualStartDate	(entity.getActualStartDate());
	       req.setActualEndDate		(entity.getActualEndDate());
	       req.setActualDays		(entity.getActualDays());
	       req.setTaskDescription	(entity.getTaskDescription());
	       req.setTaskLink			(entity.getTaskLink());
	       req.setTaskStatus		(entity.getTaskStatus());
	       req.setSendToPerson		(entity.getSendToPerson());
	       req.setActorRemark		(entity.getActorRemark());
	       req.setSendToDatetime	(entity.getSendToDatetime());
	       req.setWorkHrs			(entity.getWorkHrs());
	       req.setProjectId			(entity.getProjectId());
	       
	       req.setVersionId			(entity.getVersionId());
	//       req.setActiveFlag             (entity.getActiveFlag());
	       req.setCreateDateTime         (entity.getCreateDateTime());
	       req.setCreateUser             (entity.getCreateUser());
	      // req.setCreateProcess          (entity.getCreateProcess());
	     //  req.setUpdDateTime            (entity.getUpdDateTime());
	       req.setUpdUser                (entity.getUpdUser());
	//       req.setUpdProcess             (entity.getUpdProcess());

       return req;

	   } catch (Exception e) {
	       log.error("Show Error {}", e);
	       return null;
	   } finally {
	       req = null;
	   }
	}
	 
	 public XspAssignTask reqToEntity(XspAssignTask entity, XspAssignTaskRequest req) {

			 entity.setId				(req.getId());
			 entity.setActorId			(req.getActorId());
			 entity.setScreenId			(req.getScreenId());
			 entity.setActorRole		(req.getActorRole());
			 entity.setAssignById		(req.getAssignById());
			 entity.setAssignDatetime	(req.getAssignDatetime());
			 entity.setPlanStartDate	(req.getPlanStartDate());
			 entity.setPlanEndDate		(req.getPlanEndDate());
			 entity.setPlanDays			(req.getPlanDays());
			 entity.setActualStartDate	(req.getActualStartDate());
			 entity.setActualEndDate	(req.getActualEndDate());
			 entity.setActualDays		(req.getActualDays());
			 entity.setTaskDescription	(req.getTaskDescription());
			 entity.setTaskLink			(req.getTaskLink());
			 entity.setTaskStatus		(req.getTaskStatus());
			 entity.setSendToPerson		(req.getSendToPerson());
			 entity.setActorRemark		(req.getActorRemark());
			 entity.setSendToDatetime	(req.getSendToDatetime());
			 entity.setWorkHrs			(req.getWorkHrs());
			 entity.setProjectId		(req.getProjectId());
			 
			 entity.setVersionId		(req.getVersionId());
//         entity.setActiveFlag            (req.getActiveFlag());
			 entity.setCreateDateTime        (req.getCreateDateTime());
			 entity.setCreateUser            (req.getCreateUser());
			// entity.setCreateProcess         (req.getCreateProcess());
			// entity.setUpdDateTime           (req.getUpdDateTime());
			 entity.setUpdUser               (req.getUpdUser());
//         entity.setUpdProcess            (req.getUpdProcess());


         return entity;
     }
}
