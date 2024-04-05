package com.summitthai.cr.apprentice.jpa.xsp.person.manager;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.jpa.xsp.person.dao.XspPersonDao;
import com.summitthai.cr.apprentice.jpa.xsp.person.dto.XspPersonDto;
import com.summitthai.cr.apprentice.jpa.xsp.person.entity.XspPerson;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonResponse;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@Data
public class XspPersonManager implements XspPersonManageable {

	private static final long serialVersionUID = 2734302709208647675L;
	
	@Inject
    private ApprenticeJpaService db;
	
    @Inject
    private XspPersonDao xspPersonDao;
    
    private XspPersonDto xspPersonDto;

    public XspPersonManager() {
    	this.xspPersonDto = new XspPersonDto();
    }

    public XspPersonManager(ApprenticeJpaService db, XspPersonDao xspPersonDao) {
        this.setDb(db);
        this.setXspPersonDao(xspPersonDao);
    }

    @Override
	public XspPersonResponse findByReq(XspPersonRequest req) {
		log.debug("Begin XspPersonResponse findByReq...");
		
		XspPersonResponse  response    = null;
		XspPersonRequest   request     = null;
        //ErrorResponse             error       = null;
        List<XspPerson>    entityList  = new ArrayList<>();
        
        
        try {
        	entityList = this.xspPersonDao.findByReq(req);
            response = XspPersonResponse.builder().dataRequestList(new ArrayList<>()).build();
            
            for (XspPerson data : entityList) {
                request = this.xspPersonDto.entityToReq(data);
                response.getDataRequestList().add(request);
            }
            
            response.setStatus("SUCCESS");
            return response;
            
         }catch (Exception e) {
        	 log.error("Exception findByReq : {}", e);
        	 response.setError("ERROR");
             return response;
         } finally {
        	 log.debug("End XspPersonResponse findByReq...");
		}

	}
    
    @Override
    public XspPersonResponse findByCriteriaLikeReq(XspPersonRequest req, String suggestMode) {
        log.debug("Begin findByCriteriaLikeReq...");
        XspPersonResponse response = null;
        XspPersonRequest request =  null;
       
        List<XspPerson> entityList = new ArrayList<>();
        try {
            
            entityList = this.xspPersonDao.findByCriteriaLikeReq(req, suggestMode);
            
            response = XspPersonResponse.builder().dataRequestList(new ArrayList<>()).build();
            
            if(!entityList.isEmpty()) {
                for(XspPerson data : entityList) {
                    request = this.xspPersonDto.entityToReq(data);
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
    public XspPersonRequest getResponseAsMap(String value) {
        log.debug("get value  ==> {}", value);
        XspPersonResponse res = null;
        XspPersonRequest req = null;
        if(!StringUtils.isNullOrEmpty(value)) {
            req = XspPersonRequest.builder().build();
            req.setId(value);
            res = this.findByCriteriaLikeReq(req, null);
            if(!res.getDataRequestList().isEmpty()) {
                req = res.getDataRequestList().get(0);
            }
        } else {
            req = XspPersonRequest.builder().build();
        }
        return req;
    }

}
