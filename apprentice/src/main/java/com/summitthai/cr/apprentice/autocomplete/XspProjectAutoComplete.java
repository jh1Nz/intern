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
import com.summitthai.cr.apprentice.jpa.xsp.project.manager.XspProjectManageable;
import com.summitthai.cr.apprentice.jpa.xsp.project.model.XspProjectRequest;
import com.summitthai.cr.apprentice.jpa.xsp.project.model.XspProjectResponse;
import com.summitthai.cr.apprentice.jpa.xsp.screen.manager.XspScreenManageable;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenRequest;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenResponse;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@RequestScoped
public class XspProjectAutoComplete implements Serializable{
	
	private static final long serialVersionUID = -683644823871020210L;
	
	@Inject
	private XspProjectManageable manager;
	
	private List<XspProjectRequest> globalList;
	
	@PostConstruct
	public void init() {
        log.debug("XspProjectAutoComplete init...");
        this.globalList = new ArrayList<>();
    }
	
	private void getByCriteriaLikeReq(XspProjectRequest req, String suggestMode) {
        log.debug("Begin getByCriteriaLikeReq...");
        XspProjectResponse  res   = null;
        
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
	
	public List<XspProjectRequest> suggestByCriteriaLikeReq(String criteria) {
        log.debug("Begin suggestByCriteriaLikeReq ....");
        log.debug("query {}",criteria);
        XspProjectRequest req = null;
        String queryLowerCase = criteria.toLowerCase();
        List<XspProjectRequest> resultList = new ArrayList<>();
        
        String suggestMode = (String) UIComponent.getCurrentComponent(FacesContext.getCurrentInstance()).getAttributes().get("id");
        
        try {
           
             req = XspProjectRequest.builder().build();
             
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
