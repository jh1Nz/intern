package com.summitthai.cr.apprentice.cep.kid.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CEP_KID_EDU_COST")
@Data
public class CepKid implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "KID_UUID")
	private String kidUUID;
	
	@Column(name = "PER_UUID")
	private String perUUID;
	
	@Column(name = "KID_MOM")
	private String kidMom;
	
	@Column(name = "KID_DAD")
	private String kidDad;
	
	@Column(name = "KID_NAME")
	private String kidName;
	
	@Column(name = "KID_AGE")
	private String kidAge;
	
	@Column(name = "EDU_CLASS")
	private String eduClass;
	
	@Column(name = "EDU_LEVEL")
	private String eduLevel;
	
	@Column(name = "EDU_NAME")
	private String eduName;
	
	@Column(name = "DISBURSEMENT_TYPE")
	private String disburType;
	
	@Column(name = "EDU_AMOUNT")
	private String eduAmount;
	
}
