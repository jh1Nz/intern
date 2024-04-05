package com.summitthai.cr.apprentice.history.dao;

import java.util.List;

import com.summitthai.cr.apprentice.history.entity.History;
import com.summitthai.cr.apprentice.history.model.DataKidEducationRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface HistoryDao extends OrmDao<History>{
	
	public List<History> findByReq(DataKidEducationRequest req);
	
}
