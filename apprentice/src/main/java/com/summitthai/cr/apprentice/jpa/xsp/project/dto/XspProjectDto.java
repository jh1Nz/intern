package com.summitthai.cr.apprentice.jpa.xsp.project.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.project.entity.XspProject;
import com.summitthai.cr.apprentice.jpa.xsp.project.model.XspProjectRequest;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString
public class XspProjectDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public XspProjectRequest entityToReq(XspProject entity) {
		XspProjectRequest req = null;
	 	
		try {
		       req = XspProjectRequest.builder().build();
		       req.setId				(entity.getId());
		       req.setProjectCode		(entity.getProjectCode());
		       req.setProjectName		(entity.getProjectName());
		       req.setStartDate			(entity.getStartDate());
		       req.setEndDate			(entity.getEndDate());
		       req.setDays				(entity.getDays());
		       req.setDescriptions		(entity.getDescriptions());
		       
		//       req.setActiveFlag             (entity.getActiveFlag());
		//       req.setCreateDateTime         (entity.getCreateDateTime());
		//       req.setCreateUser             (entity.getCreateUser());
		//       req.setCreateProcess          (entity.getCreateProcess());
		//       req.setUpdDateTime            (entity.getUpdDateTime());
		//       req.setUpdUser                (entity.getUpdUser());
		//       req.setUpdProcess             (entity.getUpdProcess());

	       return req;

		   } catch (Exception e) {
		       log.error("Show Error {}", e);
		       return null;
		   } finally {
		       req = null;
		   }
		}
		
	public XspProject reqToEntity(XspProject entity, XspProjectRequest req) {

		 entity.setId					(req.getId());
		 entity.setProjectCode			(req.getProjectCode());
		 entity.setProjectName			(req.getProjectName());
		 entity.setStartDate			(req.getStartDate());
		 entity.setEndDate				(req.getEndDate());
		 entity.setDays					(req.getDays());
		 entity.setDescriptions			(req.getDescriptions());
		 
//    entity.setActiveFlag            (req.getActiveFlag());
//    entity.setCreateDateTime        (req.getCreateDateTime());
//    entity.setCreateUser            (req.getCreateUser());
//    entity.setCreateProcess         (req.getCreateProcess());
//    entity.setUpdDateTime           (req.getUpdDateTime());
//    entity.setUpdUser               (req.getUpdUser());
//    entity.setUpdProcess            (req.getUpdProcess());


    return entity;
}
}
