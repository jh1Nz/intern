package com.summitthai.cr.apprentice.cep.person.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.cep.person.entity.CepPerson;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonRequest;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString
public class CepPersonDto implements Serializable {
	private static final long serialVersionUID = 1L;

	public CepPersonRequest entityToReq(CepPerson entity) {
		CepPersonRequest req = null;
		req = CepPersonRequest.builder().build();
		try {
			
			req.setPerUUID(entity.getPerUUID());
			req.setPerType(entity.getPerType());
			req.setPosNumId(entity.getPosNumId());
			req.setPerID(entity.getPerID());
			req.setPerIdenNum(entity.getPerIdenNum());
			req.setPerTitleName(entity.getPerTitleName());
			req.setPerFirstName(entity.getPerFirstName());
			req.setPerLastName(entity.getPerLastName());
			req.setPerPosWork(entity.getPerPosWork());
			req.setPerDept(entity.getPerDept());
			req.setPerEmail(entity.getPerEmail());
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
			return req;
		} catch (Exception e) {
			log.error("Show Error {}", e);
			return req;
		} finally {
			req = null;
		}
	}
	
	public CepPerson reqToEntity(CepPerson entity, CepPersonRequest req) {
		
			entity.setPerUUID(req.getPerUUID());
			entity.setPerType(req.getPerType());
			entity.setPosNumId(req.getPosNumId());
			entity.setPerID(req.getPerID());
			entity.setPerIdenNum(req.getPerIdenNum());
			entity.setPerTitleName(req.getPerTitleName());
			entity.setPerFirstName(req.getPerFirstName());
			entity.setPerLastName(req.getPerLastName());
			entity.setPerPosWork(req.getPerPosWork());
			entity.setPerDept(req.getPerDept());
			entity.setPerEmail(req.getPerEmail());
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
		return entity;
	}

}
