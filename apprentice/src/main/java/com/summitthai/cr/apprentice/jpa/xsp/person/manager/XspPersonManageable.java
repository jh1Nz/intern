package com.summitthai.cr.apprentice.jpa.xsp.person.manager;

import java.io.Serializable;
import java.util.List;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskResponse;
import com.summitthai.cr.apprentice.jpa.xsp.person.entity.XspPerson;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonResponse;

public interface XspPersonManageable extends Serializable{

	public XspPersonResponse findByReq(XspPersonRequest req);
	
	public XspPersonResponse findByCriteriaLikeReq(XspPersonRequest req, String suggestMode);
	
	public XspPersonRequest getResponseAsMap(String value);

}
