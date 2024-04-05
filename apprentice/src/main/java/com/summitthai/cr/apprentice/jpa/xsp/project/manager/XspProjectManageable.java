package com.summitthai.cr.apprentice.jpa.xsp.project.manager;

import java.io.Serializable;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskResponse;
import com.summitthai.cr.apprentice.jpa.xsp.project.model.XspProjectRequest;
import com.summitthai.cr.apprentice.jpa.xsp.project.model.XspProjectResponse;

public interface XspProjectManageable extends Serializable{
	
	public XspProjectResponse findByReq(XspProjectRequest req);
	
	public XspProjectResponse findByCriteriaLikeReq(XspProjectRequest req, String suggestMode);
	
	public XspProjectRequest getResponseAsMap(String value);
}
