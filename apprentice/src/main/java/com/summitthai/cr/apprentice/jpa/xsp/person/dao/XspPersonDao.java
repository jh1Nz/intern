package com.summitthai.cr.apprentice.jpa.xsp.person.dao;

import java.util.List;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.person.entity.XspPerson;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface XspPersonDao extends OrmDao<XspPerson>{
	
	public List<XspPerson> findByReq(XspPersonRequest req);
	
	public List<XspPerson> findByCriteriaLikeReq(XspPersonRequest req, String suggestMode);

}
