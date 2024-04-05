package com.summitthai.cr.apprentice.jpa.xsp.screen.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;

import lombok.Data;

@Entity
@Table(name = "xsp_Screen")
@Data
public class XspScreen implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "SCREEN_ID")
	private String id;
	
	@Column(name = "SCREEN_CODE")
	private String screenCode;
	
	@Column(name = "SCREEN_NAME")
	private String screenName;
	
	@Column(name = "SCREEN_TYPE")
	private String screenType;
	
	@Column(name = "SCREEN_LEVEL")
	private String screenLevel;
	
	@Column(name = "MANDAYS")
	private String mandays;
	
	@Column(name = "DESCRIPTIONS")
	private String descriptions;
}
