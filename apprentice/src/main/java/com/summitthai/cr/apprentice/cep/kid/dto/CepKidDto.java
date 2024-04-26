package com.summitthai.cr.apprentice.cep.kid.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.cep.kid.entity.CepKid;
import com.summitthai.cr.apprentice.cep.kid.model.CepKidRequest;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString
public class CepKidDto implements Serializable {
	private static final long serialVersionUID = 1L;

	public CepKidRequest entityToReq(CepKid entity) {
		CepKidRequest req = null;
		req = CepKidRequest.builder().build();

		try {
			req.setKidUUID(entity.getKidUUID());
			req.setPerUUID(entity.getPerUUID());
			req.setKidMom(entity.getKidMom());
			req.setKidDad(entity.getKidDad());
			req.setKidAge(entity.getKidAge());
			req.setKidName(entity.getKidName());
			req.setEduClass(entity.getEduClass());
			req.setEduLevel(entity.getEduLevel());
			req.setEduName(entity.getEduName());
			req.setDisburType(entity.getDisburType());
			req.setEduAmount(entity.getEduAmount());

			return req;
		} catch (Exception e) {
			log.error("Show Error {}", e);
			return req;
		} finally {
			req = null;
		}
	}

	public CepKid reqToEntity(CepKid entity, CepKidRequest req) {
		entity.setKidUUID(req.getKidUUID());
		entity.setPerUUID(req.getPerUUID());
		entity.setKidMom(req.getKidMom());
		entity.setKidDad(req.getKidDad());
		entity.setKidAge(req.getKidAge());
		entity.setKidName(req.getKidName());
		entity.setEduClass(req.getEduClass());
		entity.setEduLevel(req.getEduLevel());
		entity.setEduName(req.getEduName());
		entity.setDisburType(req.getDisburType());
		entity.setEduAmount(req.getEduAmount());
		return entity;
	}

}
