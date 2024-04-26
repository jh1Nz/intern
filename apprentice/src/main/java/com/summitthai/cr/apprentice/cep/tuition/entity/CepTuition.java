package com.summitthai.cr.apprentice.cep.tuition.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CEP_TUITION")
@Data
public class CepTuition implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "TUITION_UUID")
	private String tuitionUUID;
	
	@Column(name = "REQ_DATE")
	private String reqDate;
	
	@Column(name = "REQ_ID")
	private String reqID;
	
	@Column(name = "REQ_STATUS")
	private String reqStatus;
	
	@Column(name = "REQ_CLAIM")
	private String reqClaim;
	
	@Column(name = "EDU_YEAR")
	private String eduYear;
	
	@Column(name = "SEMESTER")
	private String semester;
	
	@Column(name = "WELFARE_PICK")
	private String welfarePick;
	
	@Column(name = "WELFARE_AMOUNT")
	private String welfareAmount;
	
	@Column(name = "GUARANTEE_ALL")
	private String guaranteeAll;
	
	@Column(name = "GUARANTEE_KID")
	private String guaranteeKid;
	
	@Column(name = "GUARANTEE_PER")
	private String guranteePer;
	
	@Column(name = "GUARANTEE_SPOUSE")
	private String guranteeSpouse;
	
	@Column(name = "GUARANTEE_AMOUNT")
	private String guranteeAmount;
	
	@Column(name = "PER_UUID")
	private String perUUID;
}
