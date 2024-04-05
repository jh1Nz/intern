package com.summitthai.cr.apprentice.persanal.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "persanal_info")
@Data
public class PersanalInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PERSANAL_INFO_ID")
	private String persanalInfoId;
	
	@Column(name = "EMP_TYPE")
	private String empType;
	
	@Column(name = "POS_NUM")
	private String posNum;
	
	@Column(name = "EMP_ID")
	private String empId;
	
	@Column(name = "PER_ID")
	private String perId;
	
	@Column(name = "NAME_TITLE")
	private String nameTitle;
	
	@Column(name = "FRIST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "POS_JOB")
	private String posJob;
	
	@Column(name = "DEPARTMENT")
	private String depastment;
	
	

}
