package com.summitthai.cr.apprentice.view;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.summitthai.cr.apprentice.exception.DefaultException;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.manager.XspAssignTaskManageable;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskResponse;
import com.summitthai.cr.apprentice.jpa.xsp.person.entity.XspPerson;
import com.summitthai.cr.apprentice.jpa.xsp.person.manager.XspPersonManageable;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonResponse;
import com.summitthai.cr.apprentice.jpa.xsp.project.manager.XspProjectManageable;
import com.summitthai.cr.apprentice.jpa.xsp.project.model.XspProjectRequest;
import com.summitthai.cr.apprentice.jpa.xsp.project.model.XspProjectResponse;
import com.summitthai.cr.apprentice.jpa.xsp.screen.manager.XspScreenManageable;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenRequest;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenResponse;
import com.summitthai.cr.apprentice.utils.XspUtils;
import com.summitthai.sdd.faces.view.base.ViewBase;
import com.summitthai.sdd.sys.util.DateUtils;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @version 1.0.0
 * @author dachanon.ya
 * @since 2023-11-10
 * 
 */
@Named
@ViewScoped
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class Xsp01001Bean extends ViewBase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private XspAssignTaskRequest formCriteria;
	
	private XspAssignTaskRequest formEdit;
	private XspAssignTaskRequest formSelected;
	
	@Inject
	private XspAssignTaskManageable xspAssignTaskManager;
	
	private List<XspAssignTaskRequest> xspAssignTaskList;
	
	@Inject
	private XspPersonManageable xspPersonManager;
	
	@Inject
	private XspScreenManageable xspScreenManager;
	
	@Inject
	private XspProjectManageable xspProjectManager;
	
	public Xsp01001Bean() {
			this.formCriteria = XspAssignTaskRequest.builder().build();
			this.formEdit = XspAssignTaskRequest.builder().build();
			this.formSelected = XspAssignTaskRequest.builder().build();
	}
	@PostConstruct
	public void init() {
		log.debug("Begin init");
			this.mode = MODE_SEARCH;
		log.debug("End init");
	}
	public void onPageInsert() throws ParseException {
		log.debug("Begin onPageInsert");
			this.mode = MODE_INSERT;
			this.resetEdit(null);
			
		log.debug("End onPageInsert");
	}
	public void onPageSearch(ActionEvent event) {
		log.debug("Begin onPageSearch");
			this.mode = MODE_SEARCH;	
		log.debug("End onPageSearch");
	}
	public void resetEdit(ActionEvent event) {
		log.debug("Begin resetEdit");
			
		try {
			this.formEdit = XspAssignTaskRequest.builder().build();
			this.formEdit.setAssignDatetimeUi(XspUtils.convertStringToDate(XspUtils.getDate()));
		} catch (Exception e) {
			log.error("Exception {}",e);
		}
			
		log.debug("End resetEdit");
	}
	public void searchButtonOnClick() {
        log.debug("Begin...searchButtonOnClick...");
        XspAssignTaskResponse res = null;
        this.xspAssignTaskList = new ArrayList<>();
        try {
        	this.formCriteria.setDateToString();
            res = this.xspAssignTaskManager.findByReq(this.formCriteria);
            if(!res.getDataRequestList().isEmpty()) {
            	for (XspAssignTaskRequest data : res.getDataRequestList()) {
            		
            		XspPersonRequest reqPerson = XspPersonRequest.builder().build();
            		reqPerson.setId(data.getAssignById());
            		XspPersonResponse resPerson = null;
            		resPerson = this.xspPersonManager.findByReq(reqPerson);
            		
            		XspPersonRequest reqAssignTask = XspPersonRequest.builder().build();
            		reqAssignTask.setId(data.getActorId());
            		XspPersonResponse resAssignTask = null;
            		resAssignTask = this.xspPersonManager.findByReq(reqAssignTask);
            		
            		XspScreenRequest reqScreenName = XspScreenRequest.builder().build();
            		reqScreenName.setId(data.getScreenId());
            		XspScreenResponse resScreenName = null;
            		resScreenName = this.xspScreenManager.findByReq(reqScreenName);
            		
            		XspProjectRequest reqProjectId = XspProjectRequest.builder().build();
            		reqProjectId.setId(data.getProjectId());
            		XspProjectResponse resProjectId = null;
            		resProjectId = this.xspProjectManager.findByReq(reqProjectId);
            		
            		XspPersonRequest reqPersonForward = XspPersonRequest.builder().build();
            		reqPersonForward.setId(data.getSendToPerson());
            		XspPersonResponse resPersonForward = null;
            		resPersonForward = this.xspPersonManager.findByReq(reqPersonForward);
            		
            		if (!resPersonForward.getDataRequestList().isEmpty()) {
						data.setXspPersonForwardRequest(XspPersonRequest.builder().build());
						data.setXspPersonForwardRequest(resPersonForward.getDataRequestList().get(0));
					}
            		
            		if (!resPerson.getDataRequestList().isEmpty()) {
            			data.setXspPersonAssignRequest(XspPersonRequest.builder().build());
						data.setXspPersonAssignRequest(resPerson.getDataRequestList().get(0));
					}
            		if (!resAssignTask.getDataRequestList().isEmpty()) {
            			data.setXspPersonActorRequest(XspPersonRequest.builder().build());
						data.setXspPersonActorRequest(resAssignTask.getDataRequestList().get(0));
					}
            		if (!resScreenName.getDataRequestList().isEmpty()) {
            			data.setXspScreenNameRequest(XspScreenRequest.builder().build());
						data.setXspScreenNameRequest(resScreenName.getDataRequestList().get(0));
					}
            		if (!resProjectId.getDataRequestList().isEmpty()) {
						data.setXspProjectRequest(XspProjectRequest.builder().build());
						data.setXspProjectRequest(resProjectId.getDataRequestList().get(0));
					}
				}
            	this.xspAssignTaskList.addAll(res.getDataRequestList());
            	
            }
        } finally {
            log.debug("End searchButtonOnClick...");
        }
    }
	
	public void resetSearch(ActionEvent event) {
		log.debug("Begin resetSearch");
			this.formCriteria = XspAssignTaskRequest.builder().build();
			this.formCriteria.setTaskStatusList(new ArrayList<String>());
		log.debug("End resetSearch");
	}
	
	public void convestDate() {
		log.debug("Begin convestDate");
		try {
		    if (this.formCriteria.getStDateUi() != null) {
		    	String dateSt = XspUtils.convertDateToString(this.formCriteria.getStDateUi());
		    	this.formCriteria.setStDateUi(null);
				this.formCriteria.setStDateUi(XspUtils.convertStringToDate(dateSt));
			}
				
		} catch (Exception e) {
			log.error("Exception Exception {}",e);
		}
		log.debug("End convestDate");
	}
	
	private void prepareData() {
		 log.debug("Begin prepareData");
		  
		 if (this.getMode().equals(MODE_INSERT)) {
	            this.formEdit.setId(UUID.randomUUID().toString());
	            this.formEdit.setCreateDateTime(DateUtils.toTimestamp(DateUtils.getCurrentDateTimeString()));
	            this.formEdit.setCreateUser("DACHANON");
	            this.formEdit.setVersionId("0.1");
	     }
		 
		 
		 if(this.formEdit.getXspScreenNameRequest() != null && !StringUtils.isNullOrEmpty(this.formEdit.getXspScreenNameRequest().getId())) {
			 this.formEdit.setScreenId(this.formEdit.getXspScreenNameRequest().getId());
	     }
		 
		 if(this.formEdit.getXspPersonAssignRequest() != null && !StringUtils.isNullOrEmpty(this.formEdit.getXspPersonAssignRequest().getId())) {
			 this.formEdit.setAssignById(this.formEdit.getXspPersonAssignRequest().getId());
	     }
		 
		 if(this.formEdit.getXspPersonActorRequest() != null && !StringUtils.isNullOrEmpty(this.formEdit.getXspPersonActorRequest().getId())) {
			 this.formEdit.setActorId(this.formEdit.getXspPersonActorRequest().getId());
	     }
		 
		 if(this.formEdit.getXspProjectRequest() != null && !StringUtils.isNullOrEmpty(this.formEdit.getXspProjectRequest().getId())) {
			 this.formEdit.setProjectId(this.formEdit.getXspProjectRequest().getId());
		 }
		 if (this.formEdit.getXspPersonForwardRequest() != null && !StringUtils.isNullOrEmpty(this.formEdit.getXspPersonForwardRequest().getId())) {
			this.formEdit.setSendToPerson(this.formEdit.getXspPersonForwardRequest().getId());
		 }
		 
		 this.formEdit.setDateToString();
	 }
	
	public void insertButtonOnClick() {
        log.debug("Begin...insertButtonOnClick...");
        XspAssignTaskResponse   res   = null;
        try {
            this.prepareData();
            
            res = this.xspAssignTaskManager.insert(this.formEdit);

            if (res.getStatus().equals("SUCCESS")) {
                this.onPageSearch(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "INSERT_SUCCESS"));
             } else {
                 throw new DefaultException("INSERT_FAIL");
             }
        }  catch (DefaultException ex) {
            log.error("Exception :{}", ex);
            messageError(ex);
        }  catch (Exception e) {
            messageError(e);
        } finally {
            log.debug("End...insertButtonOnClick...");
            res = null;
        }
    }
	
	public void onRowSelect(SelectEvent event) {
	  log.debug("Begin onRowSelect...");


	  try {
	      this.setMode(MODE_UPDATE);
       
	           this.formEdit = XspAssignTaskRequest.builder().build();
	           this.formEdit = this.formSelected;	            

	        } catch (Exception e) {
	         log.error("Error : {}", e);
	        } finally {
	         log.debug("End onRowSelect...");
	        }
	 }
	
	public void updateButtonOnClick() {
		log.debug("Begin updateButtonOnClick...");
		XspAssignTaskResponse res = null;
		
		try {
			this.prepareData();
			

			res = this.xspAssignTaskManager.update(this.formEdit);
			if (res.getStatus().equals("SUCCESS")) {
				this.onPageSearch(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "UPDATE_COMPLETE"));
			}else {
                throw new DefaultException("UPDATE_FAIL");
            }
			
		} catch (DefaultException ex) {
            log.error("Exception :{}", ex);
            messageError(ex);
        } catch (Exception e) {
        	messageError(e);
		}finally {
			log.debug("End...updateButtonOnClick...");
            res = null;
		}

	}
	
	public void deleteButtonOnClick() {
		log.debug("Begin deletButtonOnClick...");
		XspAssignTaskResponse res = null;
		
		try {
			res = this.xspAssignTaskManager.delete(this.formEdit);
			
			if (res.getStatus().equals("SUCCESS")) {
				this.onPageSearch(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "DELETE_COMPLETE"));
			}else {
                throw new DefaultException("DELETE_FAIL");
            }
		} catch (Exception e) {
			log.error("Exception :{}", e);
            messageError(e);
		}finally {
			log.debug("End...deletButtonOnClick...");
            res = null;
		}
		
	}
}