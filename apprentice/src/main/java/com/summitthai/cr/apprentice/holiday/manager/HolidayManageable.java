package com.summitthai.cr.apprentice.holiday.manager;

import java.io.Serializable;

import com.summitthai.cr.apprentice.holiday.model.HolidayRequest;
import com.summitthai.cr.apprentice.holiday.model.HolidayResponse;

public interface HolidayManageable extends Serializable{
	public HolidayResponse findByReq(HolidayRequest req);
	
	public HolidayResponse insert(HolidayRequest req);
	
	public HolidayResponse update(HolidayRequest req);
	
	public HolidayResponse delete(HolidayRequest req);
}
