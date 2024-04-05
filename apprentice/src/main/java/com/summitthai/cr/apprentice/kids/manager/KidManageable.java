package com.summitthai.cr.apprentice.kids.manager;

import java.io.Serializable;

import com.summitthai.cr.apprentice.kids.model.DataKidRequest;
import com.summitthai.cr.apprentice.kids.model.KidInfoResponse;

public interface KidManageable extends Serializable{
	
	public KidInfoResponse findByReq(DataKidRequest req);

}
