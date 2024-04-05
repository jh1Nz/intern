package com.summitthai.cr.apprentice.kids.dao;

import java.util.List;

import com.summitthai.cr.apprentice.kids.entity.Kidinfo;
import com.summitthai.cr.apprentice.kids.model.DataKidRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface KidinfoDao extends OrmDao<Kidinfo>{
	
	public List<Kidinfo> findByReq(DataKidRequest req);
	
}
