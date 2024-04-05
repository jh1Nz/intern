package com.summitthai.cr.apprentice.persanal;
import java.io.Serializable;

import com.summitthai.cr.apprentice.persanal.model.PersanalRequest;
import com.summitthai.cr.apprentice.persanal.model.PersanalResponse;

public interface PersanalManageable extends Serializable{
	
	public PersanalResponse findByReq(PersanalRequest req);
	
	public PersanalResponse insert(PersanalRequest req);
	
	public PersanalResponse update(PersanalRequest req);
	
	public PersanalResponse delete(PersanalRequest req);
}

