package com.summitthai.cr.apprentice.jpa.xsp.screen.manager;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.persistence.jpa.rs.exceptions.ErrorResponse;

import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.dao.XspAssignTaskDao;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.dto.XspAssignTaskDto;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.manager.XspAssignTaskManageable;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.manager.XspAssignTaskManager;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskResponse;
import com.summitthai.cr.apprentice.jpa.xsp.person.entity.XspPerson;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonResponse;
import com.summitthai.cr.apprentice.jpa.xsp.screen.dao.XspScreenDao;
import com.summitthai.cr.apprentice.jpa.xsp.screen.dto.XspScreenDto;
import com.summitthai.cr.apprentice.jpa.xsp.screen.entity.XspScreen;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenRequest;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenResponse;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@Data
public class XspScreenManager implements XspScreenManageable{
	
	private static final long serialVersionUID = 2734302709208647675L;
	
	@Inject
    private ApprenticeJpaService db;
	
	@Inject
    private XspScreenDao xspScreenDao;
	
	private XspScreenDto xspScreenDto;
	
	public XspScreenManager() {
		this.xspScreenDto = new XspScreenDto();
    }
	
	public XspScreenManager(ApprenticeJpaService db, XspScreenDao xspScreenDao) {
        this.setDb(db);
       
    }
	
	@Override
	public XspScreenResponse findByReq(XspScreenRequest req) {
		log.debug("Begin XspAssignTaskResponse findByReq...");
		
		XspScreenResponse  response    = null;
		XspScreenRequest   request     = null;
        ErrorResponse      error       = null;
        List<XspScreen>    entityList  = new ArrayList<>();
        
        try {
        	entityList = this.xspScreenDao.findByReq(req);
            response = XspScreenResponse.builder().dataRequestList(new ArrayList<>()).build();
            
            for (XspScreen data : entityList) {
                request = this.xspScreenDto.entityToReq(data);
                response.getDataRequestList().add(request);
            }
            response.setStatus("SUCCESS");
            return response;
		} catch (Exception e) {
			log.error("Exception findByReq : {}", e);
       	 	response.setError("ERROR");
            return response;
		}finally {
			log.debug("End XspAssignTaskResponse findByReq...");
		}
	}
	
	@Override
    public XspScreenResponse findByCriteriaLikeReq(XspScreenRequest req, String suggestMode) {
        log.debug("Begin findByCriteriaLikeReq...");
        XspScreenResponse response = null;
        XspScreenRequest request =  null;
       
        List<XspScreen> entityList = new ArrayList<>();
        try {
            
            entityList = this.getXspScreenDao().findByCriteriaLikeReq(req, suggestMode);
            
            response = XspScreenResponse.builder().dataRequestList(new ArrayList<>()).build();
            
            if(!entityList.isEmpty()) {
                for(XspScreen data : entityList) {
                    request = this.xspScreenDto.entityToReq(data);
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
    public XspScreenRequest getResponseAsMap(String value) {
        log.debug("get value  ==> {}", value);
        XspScreenResponse res = null;
        XspScreenRequest req = null;
        if(!StringUtils.isNullOrEmpty(value)) {
            req = XspScreenRequest.builder().build();
            req.setId(value);
            res = this.findByCriteriaLikeReq(req, null);
            if(!res.getDataRequestList().isEmpty()) {
                req = res.getDataRequestList().get(0);
            }
        } else {
            req = XspScreenRequest.builder().build();
        }
        return req;
    }

}
