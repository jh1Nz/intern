package com.summitthai.cr.apprentice.person.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.person.entity.Person;
import com.summitthai.cr.apprentice.person.model.PersonRequest;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString
public class PersonDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public PersonRequest entityToReq(Person entity) {
		   PersonRequest req = null;
		   
		  try {
			  req = PersonRequest.builder().build();
			  req.setEmpID(entity.getEmpID());
			  req.setEmpType(entity.getEmpType());
			  req.setEmpPosID(entity.getEmpPosID());
			  req.setIdentityNum(entity.getIdentityNum());
			  req.setInitialName(entity.getInitialName());
			  req.setFirstName(entity.getFirstName());
			  req.setLastName(entity.getLastName());
			  req.setPosWork(entity.getPosWork());
			  req.setDepartment(entity.getDepartment());
			  req.setPerID(entity.getPerID());
			  
			  return req;
		  }catch (Exception e) {
	          log.error("Show Error {}", e);
	          return null;
	      } finally {
	          req = null;
	      }
	}
	public Person reqToEntity(Person entity, PersonRequest req) {
		entity.setEmpID(req.getEmpID());
		entity.setEmpType(req.getEmpType());
		entity.setEmpPosID(req.getEmpPosID());
		entity.setIdentityNum(req.getIdentityNum());
		entity.setInitialName(req.getInitialName());
		entity.setFirstName(req.getFirstName());
		entity.setLastName(req.getLastName());
		entity.setPosWork(req.getPosWork());
		entity.setDepartment(req.getDepartment());
		entity.setPerID(req.getPerID());
		
		return entity;
	}
	
}
