package com.summitthai.cr.apprentice.jpa.xsp.project.manager;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.persistence.jpa.rs.exceptions.ErrorResponse;

import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskResponse;
import com.summitthai.cr.apprentice.jpa.xsp.person.dao.XspPersonDao;
import com.summitthai.cr.apprentice.jpa.xsp.project.dao.XspProjectDao;
import com.summitthai.cr.apprentice.jpa.xsp.project.dto.XspProjectDto;
import com.summitthai.cr.apprentice.jpa.xsp.project.entity.XspProject;
import com.summitthai.cr.apprentice.jpa.xsp.project.model.XspProjectRequest;
import com.summitthai.cr.apprentice.jpa.xsp.project.model.XspProjectResponse;
import com.summitthai.cr.apprentice.jpa.xsp.screen.entity.XspScreen;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenRequest;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenResponse;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@Data
public class XspProjectManager implements XspProjectManageable{
	
	private static final long serialVersionUID = 2734302709208647675L;
	
	@Inject
    private ApprenticeJpaService db;
	
	@Inject
    private XspProjectDao xspProjectDao;
	
	private XspProjectDto xspProjectDto;
	
	public XspProjectManager() {
		this.xspProjectDto = new XspProjectDto();
    }
	
	public XspProjectManager(ApprenticeJpaService db, XspProjectDao xspProjectDao) {
        this.setDb(db);
    }
	
	@Override
	public XspProjectResponse findByReq(XspProjectRequest req) {
		log.debug("Begin XspProjectResponse findByReq...");
		
		XspProjectResponse  response    = null;
        XspProjectRequest   request     = null;
        ErrorResponse       error       = null;
        List<XspProject>    entityList  = new ArrayList<>();
        
        try {
        	entityList = this.xspProjectDao.findByReq(req);
            response = XspProjectResponse.builder().dataRequestList(new ArrayList<>()).build();
            
            for (XspProject data : entityList) {
                request = this.xspProjectDto.entityToReq(data);
                response.getDataRequestList().add(request);
            }
            response.setStatus("SUCCESS");
            return response;
			
		} catch (Exception e) {
			log.error("Exception findByReq : {}", e);
       	 	response.setError("ERROR");
            return response;
		}finally {
			log.debug("End XspProjectResponse findByReq...");
		}
	}
	
	@Override
    public XspProjectResponse findByCriteriaLikeReq(XspProjectRequest req, String suggestMode) {
        log.debug("Begin findByCriteriaLikeReq...");
        XspProjectResponse response = null;
        XspProjectRequest request =  null;
       
        List<XspProject> entityList = new ArrayList<>();
        try {
            
            entityList = this.getXspProjectDao().findByCriteriaLikeReq(req, suggestMode);
            
            response = XspProjectResponse.builder().dataRequestList(new ArrayList<>()).build();
            
            if(!entityList.isEmpty()) {
                for(XspProject data : entityList) {
                    request = this.xspProjectDto.entityToReq(data);
                    response.getDataRequestList().add(request);
                }
            }
            response.setStatus("SUCCESS");
            return response;
            } catch (Exception e) {
                log.error("create  Error : {}", e);
                	response.setError("ERROR");
            } finally {
                log.debug("End findByCriteriaLikeReq...");
            }
		return response;
    }
	
	@Override
    public XspProjectRequest getResponseAsMap(String value) {
        log.debug("get value  ==> {}", value);
        XspProjectResponse res = null;
        XspProjectRequest req = null;
        if(!StringUtils.isNullOrEmpty(value)) {
            req = XspProjectRequest.builder().build();
            req.setId(value);
            res = this.findByCriteriaLikeReq(req, null);
            if(!res.getDataRequestList().isEmpty()) {
                req = res.getDataRequestList().get(0);
            }
        } else {
            req = XspProjectRequest.builder().build();
        }
        return req;
    }
}
