package com.summitthai.cr.apprentice.jpa.xsp.person.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "xsp_Person")
@Data
public class XspPerson implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PERSON_ID")
	private String id;
	
	@Column(name = "PERSON_NICKNAME")
	private String personNickname;
	
	@Column(name = "PERSON_FULLNAME")
	private String personFullname;
	
	@Column(name = "PERSON_POSITION")
	private String personPosition;
	
	@Column(name = "DESCRIPTIONS")
	private String descriptions;
	
}
