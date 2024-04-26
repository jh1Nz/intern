package com.summitthai.cr.apprentice.cep.tuition.manager;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.summitthai.cr.apprentice.cep.tuition.dao.CepTuitionDao;
import com.summitthai.cr.apprentice.cep.tuition.dto.CepTuitionDto;
import com.summitthai.cr.apprentice.cep.tuition.entity.CepTuition;
import com.summitthai.cr.apprentice.cep.tuition.model.CepTuitionRequest;
import com.summitthai.cr.apprentice.cep.tuition.model.CepTuitionResponse;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.status.SimpleStatus;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@Data
public class CepTuitionManager implements CepTuitionManageable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ApprenticeJpaService db;

	@Inject
	private CepTuitionDao cepTuitionDao;

	private CepTuitionDto cepTuitionDto;

	public CepTuitionManager() {
		this.cepTuitionDto = new CepTuitionDto();
	}

	public CepTuitionManager(ApprenticeJpaService db, CepTuitionDao cepTuitionDao) {
		this.setDb(db);

	}

	@Override
	public CepTuitionResponse findByReq(CepTuitionRequest req) {
		log.debug("Begin findByReq");
		CepTuitionRequest request = null;
		CepTuitionResponse res = null;
		List<CepTuition> entityList = new ArrayList<>();

		try {
			entityList = this.cepTuitionDao.findByReq(req);
			res = CepTuitionResponse.builder().dataRequestList(new ArrayList<>()).build();
			if (!entityList.isEmpty()) {
				for (CepTuition data : entityList) {
					request = this.cepTuitionDto.entityToReq(data);
					res.getDataRequestList().add(request);
				}
			}
			res.setStatus(SimpleStatus.SUCCESS);
			return res;

		} catch (Exception e) {
			log.error("Exception findByReq : {}", e);
			res = CepTuitionResponse.builder().status(SimpleStatus.FAIL).build();
			return res;
		} finally {
			log.debug("End findByReq...");
		}

	}

}
