package com.summitthai.cr.apprentice.deptH.manager;

import java.io.Serializable;

import com.summitthai.cr.apprentice.deptH.model.HolidayDeptRequest;
import com.summitthai.cr.apprentice.deptH.model.HolidayDeptResponse;

public interface HolidayDeptManageable extends  Serializable{
	public HolidayDeptResponse findByReq(HolidayDeptRequest req);
}
