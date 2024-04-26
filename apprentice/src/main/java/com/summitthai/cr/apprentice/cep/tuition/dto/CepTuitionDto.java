package com.summitthai.cr.apprentice.cep.tuition.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.cep.tuition.entity.CepTuition;
import com.summitthai.cr.apprentice.cep.tuition.model.CepTuitionRequest;
import com.summitthai.cr.apprentice.utils.XspUtils;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString
public class CepTuitionDto implements Serializable{
	private static final long serialVersionUID = 1L;

	public CepTuitionRequest entityToReq(CepTuition entity) {
		CepTuitionRequest req = null;
		req = CepTuitionRequest.builder().build();
		
		try {
			req.setTuitionUUID(entity.getTuitionUUID());
			req.setReqDate(XspUtils.convertStringToDate(entity.getReqDate()));
			req.setReqID(entity.getReqID());
			req.setReqStatus(entity.getReqStatus());
			req.setReqClaim(entity.getReqClaim());
			req.setEduYear(entity.getEduYear());
			req.setSemester(entity.getSemester());
			req.setWelfarePick(entity.getWelfarePick());
			req.setWelfareAmount(entity.getWelfareAmount());
			req.setGuaranteeAll(entity.getGuaranteeAll());
			req.setGuaranteeKid(entity.getGuaranteeKid());
			req.setGuranteePer(entity.getGuranteePer());
			req.setGuranteeSpouse(entity.getGuranteeSpouse());
			req.setGuranteeAmount(entity.getGuranteeAmount());
			req.setPerUUID(entity.getPerUUID());
			
			return req;
		} catch (Exception e) {
			log.error("Show Error {}", e);
			return null;
		} finally {
			req = null;
		}
		
	}
	
	public CepTuition reqToEntity(CepTuition entity,CepTuitionRequest req) {
		entity.setTuitionUUID(req.getTuitionUUID());
		entity.setReqDate(XspUtils.convertDateToString(req.getReqDate()));
		entity.setReqID(req.getReqID());
		entity.setReqStatus(req.getReqStatus());
		entity.setReqClaim(req.getReqClaim());
		entity.setEduYear(req.getEduYear());
		entity.setSemester(req.getSemester());
		entity.setWelfarePick(req.getWelfarePick());
		entity.setWelfareAmount(req.getWelfareAmount());
		entity.setGuaranteeAll(req.getGuaranteeAll());
		entity.setGuaranteeKid(req.getGuaranteeKid());
		entity.setGuranteePer(req.getGuranteePer());
		entity.setGuranteeSpouse(req.getGuranteeSpouse());
		entity.setGuranteeAmount(req.getGuranteeAmount());
		entity.setPerUUID(req.getPerUUID());
		return entity;
	}
}
