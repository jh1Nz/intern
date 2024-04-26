package com.summitthai.cr.apprentice.cep.spouse.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.cep.spouse.entity.CepSpouse;
import com.summitthai.cr.apprentice.cep.spouse.model.CepSpouseRequest;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString
public class CepSpouseDto implements Serializable {
	private static final long serialVersionUID = 1L;

	public CepSpouseRequest entityToReq(CepSpouse entity) {
		CepSpouseRequest req = null;
		req = CepSpouseRequest.builder().build();

		try {
			req.setSpouseUUID(entity.getSpouseUUID());
			req.setSpouseTitleName(entity.getSpouseTitleName());
			req.setSpouseFirstName(entity.getSpouseFirstName());
			req.setSpouseLastName(entity.getSpouseLastName());
			req.setSpouseRelation(entity.getSpouseRelation());
			req.setSpouseStatus(entity.getSpouseStatus());
			req.setSpouseJob(entity.getSpouseJob());
			req.setSpouseOtherJob(entity.getSpouseOtherJob());
			req.setCheckGovEmp(entity.getCheckGovEmp());
			req.setGov(entity.getGov());
			req.setEmp(entity.getEmp());
			req.setEntGov(entity.getEntGov());
			req.setOrgBkk(entity.getOrgBkk());
			req.setPos(entity.getPos());
			req.setAffiliationn(entity.getAffiliationn());
			req.setPosSub(entity.getPosSub());
			req.setAffiliationnSub(entity.getAffiliationnSub());
			req.setPerUUID(entity.getPerUUID());
			return req;
		} catch (Exception e) {
			log.error("Show Error {}", e);
			return req;
		} finally {
			req = null;
		}
	}

	public CepSpouse reqToEntity(CepSpouse entity, CepSpouseRequest req) {
		entity.setSpouseUUID(req.getSpouseUUID());
		entity.setSpouseTitleName(req.getSpouseTitleName());
		entity.setSpouseFirstName(req.getSpouseFirstName());
		entity.setSpouseLastName(req.getSpouseLastName());
		entity.setSpouseRelation(req.getSpouseRelation());
		entity.setSpouseStatus(req.getSpouseStatus());
		entity.setSpouseJob(req.getSpouseJob());
		entity.setSpouseOtherJob(req.getSpouseOtherJob());
		entity.setCheckGovEmp(req.getCheckGovEmp());
		entity.setGov(req.getGov());
		entity.setEmp(req.getEmp());
		entity.setEntGov(req.getEntGov());
		entity.setOrgBkk(req.getOrgBkk());
		entity.setPos(req.getPos());
		entity.setAffiliationn(req.getAffiliationn());
		entity.setPosSub(req.getPosSub());
		entity.setAffiliationnSub(req.getAffiliationnSub());
		entity.setPerUUID(req.getPerUUID());
		return entity;
	}

}
