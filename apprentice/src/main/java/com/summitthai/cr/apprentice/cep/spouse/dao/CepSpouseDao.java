package com.summitthai.cr.apprentice.cep.spouse.dao;

import java.util.List;

import com.summitthai.cr.apprentice.cep.spouse.entity.CepSpouse;
import com.summitthai.cr.apprentice.cep.spouse.model.CepSpouseRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface CepSpouseDao extends OrmDao<CepSpouse>{
	public List<CepSpouse> findByReq(CepSpouseRequest req);
}
