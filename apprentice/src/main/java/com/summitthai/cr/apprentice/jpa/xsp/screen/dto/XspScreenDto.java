package com.summitthai.cr.apprentice.jpa.xsp.screen.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.dto.XspAssignTaskDto;
import com.summitthai.cr.apprentice.jpa.xsp.person.entity.XspPerson;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;
import com.summitthai.cr.apprentice.jpa.xsp.screen.entity.XspScreen;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenRequest;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString
public class XspScreenDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public XspScreenRequest entityToReq(XspScreen entity) {
		   XspScreenRequest req = null;

	  try {
	      req = XspScreenRequest.builder().build();
	      req.setId      				(entity.getId());
	      req.setScreenCode				(entity.getScreenCode());
	      req.setScreenName				(entity.getScreenName());
	      req.setScreenType				(entity.getScreenType());
	      req.setScreenLevel			(entity.getScreenLevel());
	      req.setMandays				(entity.getMandays());
	      req.setDescriptions			(entity.getDescriptions());
	      
	//      req.setActiveFlag             (entity.getActiveFlag());
	//      req.setCreateDateTime         (entity.getCreateDateTime());
	//      req.setCreateUser             (entity.getCreateUser());
	//      req.setCreateProcess          (entity.getCreateProcess());
	//      req.setUpdDateTime            (entity.getUpdDateTime());
	//      req.setUpdUser                (entity.getUpdUser());
	//      req.setUpdProcess             (entity.getUpdProcess());
	
	      return req;
	
	  } catch (Exception e) {
	      log.error("Show Error {}", e);
	      return null;
	  } finally {
	      req = null;
	  }
	 }
		
	public XspScreen reqToEntity(XspScreen entity, XspScreenRequest req) {

        entity.setId					(req.getId());
        entity.setScreenCode			(entity.getScreenCode());
        entity.setScreenName			(entity.getScreenName());
        entity.setScreenType			(entity.getScreenType());
        entity.setScreenLevel			(entity.getScreenLevel());
        entity.setMandays				(req.getMandays());
        entity.setDescriptions			(req.getDescriptions());

//        entity.setActiveFlag            (req.getActiveFlag());
//        entity.setCreateDateTime        (req.getCreateDateTime());
//        entity.setCreateUser            (req.getCreateUser());
//        entity.setCreateProcess         (req.getCreateProcess());
//        entity.setUpdDateTime           (req.getUpdDateTime());
//        entity.setUpdUser               (req.getUpdUser());
//        entity.setUpdProcess            (req.getUpdProcess());


        return entity;
    }
}
