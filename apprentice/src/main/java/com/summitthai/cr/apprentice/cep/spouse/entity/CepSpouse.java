package com.summitthai.cr.apprentice.cep.spouse.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CEO_SPOUSE")
@Data
public class CepSpouse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "SPOUSE_UUID")
	private String spouseUUID;
	
	@Column(name = "SPOUSE_TITLE_NAME")
	private String spouseTitleName;
	
	@Column(name = "SPOUSE_FIRST_NAME")
	private String spouseFirstName;

	@Column(name = "SPOUSE_LAST_NAME")
	private String spouseLastName;
	
	@Column(name = "SPOUSE_RELATION")
	private String spouseRelation;
	
	@Column(name = "SPOUSE_STATUS")
	private String spouseStatus;
	
	@Column(name = "SPOUSE_JOB")
	private String spouseJob;
	
	@Column(name = "SPOUSE_OTHER_JOB")
	private String spouseOtherJob;
	
	@Column(name = "CHECK_GOV_EMP")
	private String checkGovEmp;
	
	@Column(name = "GOV")
	private String gov;
	
	@Column(name = "EMP")
	private String emp;
	
	@Column(name = "ENT_GOV")
	private String entGov;
	
	@Column(name = "ORG_BKK")
	private String orgBkk;
	
	@Column(name = "POS")
	private String pos;
	
	@Column(name = "AFFILIATION")
	private String affiliationn;
	
	@Column(name = "POS_SUB")
	private String posSub;
	
	@Column(name = "AFFILIATION_SUB")
	private String affiliationnSub;
	
	@Column(name = "PER_UUID")
	private String perUUID;
	

}
