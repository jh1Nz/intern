package com.summitthai.cr.apprentice.persanal.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.persanal.entity.PersanalInfo;
import com.summitthai.cr.apprentice.persanal.model.PersanalRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersanalDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public PersanalRequest entityToReq(PersanalInfo entity) {
		PersanalRequest req = null;
	 	
		try {
		       req = PersanalRequest.builder().build();
		       
		       req.setEmpType		(entity.getEmpType());
		       req.setPosNum		(entity.getPosNum());
		       req.setEmpId		(entity.getEmpId());
		       req.setPerId		(entity.getPerId());
		       req.setNameTitle		(entity.getNameTitle());
		       req.setFirstName	(entity.getFirstName());
		       req.setLastName		(entity.getLastName());
		       req.setPosJob		(entity.getPosJob());
		       req.setDepastment		(entity.getDepastment());
		       req.setPersanalInfoId		(entity.getPersanalInfoId());
		       

	       return req;
	       

		   } catch (Exception e) {
		       log.error("Exception entityToReq {}",e);
		       return null;
		   } finally {
		       req = null;
		   }
		}
	
	public PersanalInfo reqToEntity(PersanalInfo entity, PersanalRequest req) {

		entity.setEmpType		(req.getEmpType());
		entity.setPosNum		(req.getPosNum());
		entity.setEmpId		(req.getEmpId());
		entity.setPerId		(req.getPerId());
		entity.setNameTitle		(req.getNameTitle());
		entity.setFirstName	(req.getFirstName());
		entity.setLastName		(req.getLastName());
		entity.setPosJob		(req.getPosJob());
		entity.setDepastment		(req.getDepastment());
		entity.setPersanalInfoId		(req.getPersanalInfoId());
	       



   return entity;
}

}
