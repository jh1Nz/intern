package com.summitthai.cr.apprentice.cep.person.dao;

import java.util.List;

import com.summitthai.cr.apprentice.cep.person.entity.CepPerson;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface CepPersonDao extends OrmDao<CepPerson>{
	public List<CepPerson> findByReq(CepPersonRequest req); 
}
