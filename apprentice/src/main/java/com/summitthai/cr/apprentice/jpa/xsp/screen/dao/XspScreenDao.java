package com.summitthai.cr.apprentice.jpa.xsp.screen.dao;

import java.util.List;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.screen.entity.XspScreen;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface XspScreenDao extends OrmDao<XspScreen>{
	
	public List<XspScreen> findByReq(XspScreenRequest req);
	
	public List<XspScreen> findByCriteriaLikeReq(XspScreenRequest req, String suggestMode);
	
}
