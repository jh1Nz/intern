package com.summitthai.cr.apprentice.deptH.dao;

import java.util.List;

import com.summitthai.cr.apprentice.deptH.entity.HolidayDept;
import com.summitthai.cr.apprentice.deptH.model.HolidayDeptRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface HolidayDeptDao extends OrmDao<HolidayDept>{
	public List<HolidayDept> findByReq(HolidayDeptRequest req);
}
