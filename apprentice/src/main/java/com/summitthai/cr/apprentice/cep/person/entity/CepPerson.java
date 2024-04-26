package com.summitthai.cr.apprentice.cep.person.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CEP_PERSON")
@Data
public class CepPerson implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PER_UUID")
	private String perUUID;
	
	@Column(name = "PER_TYPE")
	private String perType;
	
	@Column(name = "POS_NUM_ID")
	private String posNumId;
	
	@Column(name = "PER_ID")
	private String perID;
	
	@Column(name = "PER_IDEN_NUM")
	private String perIdenNum;
	
	@Column(name = "PER_TITLE_NAME")
	private String perTitleName;
	
	@Column(name = "PER_FIRST_NAME")
	private String perFirstName;
	
	@Column(name = "PER_LAST_NAME")
	private String perLastName;
	
	@Column(name = "PER_POS_WORK")
	private String perPosWork;
	
	@Column(name = "PER_DEPT")
	private String perDept;
	
	@Column(name = "PER_EMAIL")
	private String perEmail;
	
	
	
	
}
