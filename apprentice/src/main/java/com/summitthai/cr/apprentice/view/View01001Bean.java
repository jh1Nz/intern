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
import com.summitthai.cr.apprentice.jpa.xsp.assign.personal.model.XspAssignPersanalRequest;
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
public class View01001Bean extends ViewBase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String search;
	private String insert;
	private String delete;
	private String view;
	private String process;
	private String report;
	private String update;
	public View01001Bean() {
		
			
	}
	@PostConstruct
	public void init() {
		log.debug("Begin init");
		this.search= MODE_SEARCH;
		this.mode = search;
		log.debug("MODE :{}",search);
		log.debug("End init");
	}
	public void onPageInsert(ActionEvent event)  {
		log.debug("Begin onPageInsert");
		this.insert= MODE_INSERT;
		this.mode = insert;
		log.debug("MODE :{}",insert);
		log.debug("End onPageInsert");
	}
	public void onPageSearch(ActionEvent event) {
		log.debug("Begin onPageSearch");
			this.search = MODE_SEARCH;
			this.mode = search;
			log.debug("MODE :{}",search);
		log.debug("End onPageSearch");
	}
	public void onPageUpdate(ActionEvent event) {
		log.debug("Begin onPageUpdate");
			this.update = MODE_UPDATE;
			this.mode = update;
			log.debug("MODE :{}",update);
		log.debug("End onPageUpdate");
	}
	public void onPageProcess(ActionEvent event) {
		log.debug("Begin onPageProcess");
			this.process = MODE_PROCESS;
			this.mode = process;
			log.debug("MODE :{}",process);
		log.debug("End onPageProcess");
	}
	public void onPageView(ActionEvent event) {
		log.debug("Begin onPageView");
			this.view = MODE_VIEW;	
			this.mode = view;
			log.debug("MODE :{}",view);
		log.debug("End onPageView");
		
	}
	public void onPageReport(ActionEvent event) {
		log.debug("Begin onPageReport");
			this.report = MODE_REPORT;	
			this.mode = report;
			log.debug("MODE :{}",report);
		log.debug("End onPageReport");
		
	}
	
	public void onPageDelete(ActionEvent event) {
		log.debug("Begin onPageDelete");
			this.delete = MODE_DELETE;
			this.mode = delete;
			log.debug("MODE :{}",delete);
		log.debug("End onPageDelete");
		
	}
	
	
	
}