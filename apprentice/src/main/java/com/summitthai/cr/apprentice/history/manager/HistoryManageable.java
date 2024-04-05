package com.summitthai.cr.apprentice.history.manager;

import java.io.Serializable;

import com.summitthai.cr.apprentice.history.model.DataKidEducationRequest;
import com.summitthai.cr.apprentice.history.model.HistoryResponse;

public interface HistoryManageable extends Serializable{
	
public HistoryResponse findByReq(DataKidEducationRequest req);
	
	

}
