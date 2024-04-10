package com.summitthai.cr.apprentice.person.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PERSONAL_DATA")
@Data
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EMP_ID")
	private String empID;

	@Column(name = "EMP_TYPE")
	private String empType;

	@Column(name = "EMP_POS_ID")
	private String empPosID;

	@Column(name = "IDENTITY_NUM")
	private String identityNum;

	@Column(name = "INITIAL_NAME")
	private String initialName;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "POS_WORK")
	private String posWork;

	@Column(name = "DEPARTMENT")
	private String department;

	@Column(name = "PER_ID")
	private String perID;
}
