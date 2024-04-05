package com.summitthai.cr.apprentice.jpa.xsp.person.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.jpa.xsp.person.entity.XspPerson;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString
public class XspPersonDto  implements Serializable{

	 private static final long serialVersionUID = 1L;
	 
	 public XspPersonRequest entityToReq(XspPerson entity) {
		 	XspPersonRequest req = null;

      try {
          req = XspPersonRequest.builder().build();
          req.setId      				(entity.getId());
          req.setPersonNickname			(entity.getPersonNickname());
          req.setPersonFullname			(entity.getPersonFullname());
          req.setPersonPosition			(entity.getPersonPosition());
          req.setDescriptions			(entity.getDescriptions());
          
//          req.setActiveFlag             (entity.getActiveFlag());
//          req.setCreateDateTime         (entity.getCreateDateTime());
//          req.setCreateUser             (entity.getCreateUser());
//          req.setCreateProcess          (entity.getCreateProcess());
//          req.setUpdDateTime            (entity.getUpdDateTime());
//          req.setUpdUser                (entity.getUpdUser());
//          req.setUpdProcess             (entity.getUpdProcess());

          return req;

      } catch (Exception e) {
          log.error("Show Error {}", e);
          return null;
      } finally {
          req = null;
      }
	 }
	 
	 public XspPerson reqToEntity(XspPerson entity, XspPersonRequest req) {

         entity.setId					(req.getId());
         entity.setPersonNickname		(req.getPersonNickname());
         entity.setPersonFullname		(req.getPersonFullname());
         entity.setPersonPosition		(req.getPersonPosition());
         entity.setDescriptions			(req.getDescriptions());

//         entity.setActiveFlag            (req.getActiveFlag());
//         entity.setCreateDateTime        (req.getCreateDateTime());
//         entity.setCreateUser            (req.getCreateUser());
//         entity.setCreateProcess         (req.getCreateProcess());
//         entity.setUpdDateTime           (req.getUpdDateTime());
//         entity.setUpdUser               (req.getUpdUser());
//         entity.setUpdProcess            (req.getUpdProcess());


         return entity;
     }

}

