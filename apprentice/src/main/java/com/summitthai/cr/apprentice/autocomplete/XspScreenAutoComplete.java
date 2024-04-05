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
import com.summitthai.cr.apprentice.jpa.xsp.screen.manager.XspScreenManageable;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenRequest;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenResponse;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@RequestScoped
public class XspScreenAutoComplete implements Serializable{
	
	private static final long serialVersionUID = -683644823871020210L;
	
	@Inject
	private XspScreenManageable manager;
	
	private List<XspScreenRequest> globalList;
	
	@PostConstruct
	public void init() {
        log.debug("XspScreenAutoComplete init...");
        this.globalList = new ArrayList<>();
    }
	
	private void getByCriteriaLikeReq(XspScreenRequest req, String suggestMode) {
        log.debug("Begin getByCriteriaLikeReq...");
        XspScreenResponse  res   = null;
        
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
	
	public List<XspScreenRequest> suggestByCriteriaLikeReq(String criteria) {
        log.debug("Begin suggestByCriteriaLikeReq ....");
        log.debug("query {}",criteria);
        XspScreenRequest req = null;
        String queryLowerCase = criteria.toLowerCase();
        List<XspScreenRequest> resultList = new ArrayList<>();
        
        String suggestMode = (String) UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("id");
        
        try {
           
             req = XspScreenRequest.builder().build();
             
             if(!StringUtils.isNullOrEmpty(queryLowerCase)) {
                 req.setId(queryLowerCase);
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
