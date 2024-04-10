package com.summitthai.cr.apprentice.holiday.dao;

import java.util.List;

import com.summitthai.cr.apprentice.holiday.entity.Holiday;
import com.summitthai.cr.apprentice.holiday.model.HolidayRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface HolidayDao extends OrmDao<Holiday>{
	public List<Holiday> findByReq(HolidayRequest req);
	
}
