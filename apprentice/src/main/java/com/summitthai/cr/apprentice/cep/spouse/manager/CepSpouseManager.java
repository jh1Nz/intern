package com.summitthai.cr.apprentice.cep.spouse.manager;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.summitthai.cr.apprentice.cep.spouse.dao.CepSpouseDao;
import com.summitthai.cr.apprentice.cep.spouse.dto.CepSpouseDto;
import com.summitthai.cr.apprentice.cep.spouse.entity.CepSpouse;
import com.summitthai.cr.apprentice.cep.spouse.model.CepSpouseRequest;
import com.summitthai.cr.apprentice.cep.spouse.model.CepSpouseResponse;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.status.SimpleStatus;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@Data
public class CepSpouseManager implements CepSpouseManageable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ApprenticeJpaService db;
	
	@Inject
	private CepSpouseDao cepSpouseDao;
	
	private CepSpouseDto cepSpouseDto;
	
	public CepSpouseManager() {
		this.cepSpouseDto = new CepSpouseDto();
	}

	public CepSpouseManager(ApprenticeJpaService db, CepSpouseDao cepSpouseDao) {
		this.setDb(db);

	}
	
	@Override
	public CepSpouseResponse findByReq(CepSpouseRequest req) {
		log.debug("Begin findByReq");
		CepSpouseRequest request = null;
		CepSpouseResponse res = null;
		List<CepSpouse> entityList = new ArrayList<>();

		try {
			entityList = this.cepSpouseDao.findByReq(req);
			res = CepSpouseResponse.builder().dataRequestList(new ArrayList<>()).build();
			if (!entityList.isEmpty()) {
				for (CepSpouse data : entityList) {
					request = this.cepSpouseDto.entityToReq(data);
					res.getDataRequestList().add(request);
				}
			}
			res.setStatus(SimpleStatus.SUCCESS);
			return res;

		} catch (Exception e) {
			log.error("Exception findByReq : {}", e);
			res = CepSpouseResponse.builder().status(SimpleStatus.FAIL).build();
			return res;
		} finally {
			log.debug("End findByReq...");
		}
	}

}
