package com.summitthai.cr.apprentice.autocomplete;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.summitthai.cr.apprentice.jpa.xsp.person.manager.XspPersonManageable;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonResponse;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@RequestScoped
public class XspPersonAutoComplete implements Serializable{
	
	private static final long serialVersionUID = -683644823871020210L;
	
	@Inject
	private XspPersonManageable manager;
	
	private List<XspPersonRequest> globalList; 
	
	@PostConstruct
	public void init() {
        log.debug("XspPersonAutoComplete init...");
        this.globalList = new ArrayList<>();
    }
	
	
	private void getByCriteriaLikeReq(XspPersonRequest req, String suggestMode) {
        log.debug("Begin getByCriteriaLikeReq...");
        XspPersonResponse  res   = null;
        
        try {
            res = this.manager.findByCriteriaLikeReq(req, suggestMode);
            
            this.globalList.addAll(res.getDataRequestList());
            log.debug(" globalList = {}",globalList.size());
        } catch (Exception e) {
         log.error("Exception {}", e);
        } finally {
            log.debug("End getByCriteriaLikeReq...");
        }
    }
	
	
	public List<XspPersonRequest> suggestByCriteriaLikeReq(String criteria) {
	        log.debug("Begin suggestByCriteriaLikeReq ....");
	        log.debug("query {}",criteria);
	        XspPersonRequest req = null;
	        String queryLowerCase = criteria.toLowerCase();
	        List<XspPersonRequest> resultList = new ArrayList<>();
	        
	        String suggestMode = (String) UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("personNickname");
	        
	        try {
	           
	             req = XspPersonRequest.builder().build();
	             
	             if(!StringUtils.isNullOrEmpty(queryLowerCase)) {
	                 req.setPersonNickname(queryLowerCase);
	             }

	            if(!StringUtils.isNullOrEmpty(suggestMode)) {
	                this.getByCriteriaLikeReq(req, suggestMode);
	            } else {
	                
	                this.getByCriteriaLikeReq(req, null);
	            }
	            resultList = this.globalList;
	            return resultList;
	        } catch (Exception e) {
	            log.error("Exception {}", e);
	            return new ArrayList<>();
	        } finally {
	            log.debug("End suggestByCriteriaLikeReq ....");
	        }
	    }

}
