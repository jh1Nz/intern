package com.summitthai.cr.apprentice.cep.tuition.dao;

import java.util.List;

import com.summitthai.cr.apprentice.cep.tuition.entity.CepTuition;
import com.summitthai.cr.apprentice.cep.tuition.model.CepTuitionRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface CepTuitionDao extends OrmDao<CepTuition>{
	public List<CepTuition> findByReq(CepTuitionRequest req);
}
