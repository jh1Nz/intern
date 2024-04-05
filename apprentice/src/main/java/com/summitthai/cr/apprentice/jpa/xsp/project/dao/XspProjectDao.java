package com.summitthai.cr.apprentice.jpa.xsp.project.dao;

import java.util.List;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.project.entity.XspProject;
import com.summitthai.cr.apprentice.jpa.xsp.project.model.XspProjectRequest;
import com.summitthai.sdd.dao.OrmDao;

public interface XspProjectDao extends OrmDao<XspProject>{

	public List<XspProject> findByReq(XspProjectRequest req);
	
	public List<XspProject> findByCriteriaLikeReq(XspProjectRequest req, String suggestMode);
}
