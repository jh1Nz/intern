package com.summitthai.cr.apprentice.cep.person.manager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.summitthai.cr.apprentice.cep.person.dao.CepPersonDao;
import com.summitthai.cr.apprentice.cep.person.dto.CepPersonDto;
import com.summitthai.cr.apprentice.cep.person.entity.CepPerson;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonRequest;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonResponse;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.status.SimpleStatus;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@Data
public class CepPersonManager implements CepPersonManageable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ApprenticeJpaService db;

	@Inject
	private CepPersonDao cepPersonDao;

	private CepPersonDto cepPersonDto;

	public CepPersonManager() {
		this.cepPersonDto = new CepPersonDto();
	}

	public CepPersonManager(ApprenticeJpaService db, CepPersonDao cepPersonDao) {
		this.setDb(db);
	}

	@Override
	public CepPersonResponse findByReq(CepPersonRequest req) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public CepPersonResponse insert(CepPersonRequest req) {
		// TODO Auto-generated method stub
		log.debug("Begin Insert Manager...");
		CepPersonResponse response = null;
		CepPerson entity = new CepPerson();
		
		try {
			this.db.begin();			
			entity = this.cepPersonDto.reqToEntity(entity, req);		
			this.cepPersonDao.create(entity);	
			this.db.commit();
			response = CepPersonResponse.builder().status(SimpleStatus.SUCCESS).build();
			return response;
		}catch (Exception e) {
			this.db.rollback();
			log.error("Exception : {}", e);
			response.setError(SimpleStatus.ERROR);
			return response;
		} finally {
			log.debug("End insert....");
			response = null;
		}
		
	}
	
	@Override
	public CepPersonResponse update(CepPersonRequest req) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public CepPersonResponse delete(CepPersonRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}
