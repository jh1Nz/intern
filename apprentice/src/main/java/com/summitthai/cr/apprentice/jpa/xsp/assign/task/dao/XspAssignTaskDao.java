package com.summitthai.cr.apprentice.jpa.xsp.assign.task.dao;

import java.util.List;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface XspAssignTaskDao extends OrmDao<XspAssignTask>{
	
	public List<XspAssignTask> findByReq(XspAssignTaskRequest req);
	
}
