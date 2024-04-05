package com.summitthai.cr.apprentice.jpa.xsp.screen.manager;

import java.io.Serializable;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskResponse;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenRequest;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenResponse;

public interface XspScreenManageable extends Serializable{
	
	public XspScreenResponse findByReq(XspScreenRequest req);
	
	public XspScreenResponse findByCriteriaLikeReq(XspScreenRequest req, String suggestMode);
	
	public XspScreenRequest getResponseAsMap(String value);
}
