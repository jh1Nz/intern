package com.summitthai.cr.apprentice.cep.kid.manager;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.summitthai.cr.apprentice.cep.kid.dao.CepKidDao;
import com.summitthai.cr.apprentice.cep.kid.dto.CepKidDto;
import com.summitthai.cr.apprentice.cep.kid.entity.CepKid;
import com.summitthai.cr.apprentice.cep.kid.model.CepKidRequest;
import com.summitthai.cr.apprentice.cep.kid.model.CepKidResponse;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.status.SimpleStatus;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@Data
public class CepKidManager implements CepKidManageable{
private static final long serialVersionUID = 1L;
	
	@Inject
	private ApprenticeJpaService db;
	
	@Inject
	private CepKidDao cepKidDao;
	
	private CepKidDto cepKidDto;
	
	public CepKidManager() {
		this.cepKidDto = new CepKidDto();
	}
	
	public CepKidManager(ApprenticeJpaService db, CepKidDao cepKidDao) {
		this.setDb(db);

	}
	
	@Override
	public CepKidResponse findByReq(CepKidRequest req) {
		log.debug("Begin findByReq");
		CepKidRequest request = null;
		CepKidResponse res = null;
		List<CepKid> entityList = new ArrayList<>();

		try {
			entityList = this.cepKidDao.findByReq(req);
			res = CepKidResponse.builder().dataRequestList(new ArrayList<>()).build();
			if (!entityList.isEmpty()) {
				for (CepKid data : entityList) {
					request = this.cepKidDto.entityToReq(data);
					res.getDataRequestList().add(request);
				}
			}
			res.setStatus(SimpleStatus.SUCCESS);
			return res;

		} catch (Exception e) {
			log.error("Exception findByReq : {}", e);
			res = CepKidResponse.builder().status(SimpleStatus.FAIL).build();
			return res;
		} finally {
			log.debug("End findByReq...");
		}
	}
}
