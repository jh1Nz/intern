package com.summitthai.cr.apprentice.persanal.dao;

import java.util.List;

import com.summitthai.cr.apprentice.persanal.entity.PersanalInfo;
import com.summitthai.cr.apprentice.persanal.model.PersanalRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface PersanalDao  extends OrmDao<PersanalInfo>{
	
	public List<PersanalInfo> findByReq(PersanalRequest req);
	

}
