package com.summitthai.cr.apprentice.jpa.xsp.assign.task.manager;

import java.io.Serializable;
import java.util.List;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskResponse;
import com.summitthai.cr.apprentice.jpa.xsp.person.entity.XspPerson;

public interface XspAssignTaskManageable extends Serializable{
	
	public XspAssignTaskResponse findByReq(XspAssignTaskRequest req);
	
	public XspAssignTaskResponse insert(XspAssignTaskRequest req);
	
	public XspAssignTaskResponse update(XspAssignTaskRequest req);
	
	public XspAssignTaskResponse delete(XspAssignTaskRequest req);
}
