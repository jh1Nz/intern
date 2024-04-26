package com.summitthai.cr.apprentice.cep.kid.dao;

import java.util.List;

import com.summitthai.cr.apprentice.cep.kid.entity.CepKid;
import com.summitthai.cr.apprentice.cep.kid.model.CepKidRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface CepKidDao extends OrmDao<CepKid>{
	public List<CepKid> findByReq(CepKidRequest req);
}
