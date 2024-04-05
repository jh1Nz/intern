package com.summitthai.cr.apprentice.kids.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "kid_info")
@Data
public class Kidinfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "KID_INFO_ID")
	private String kidInfoId;
	
	@Column(name = "KID_TYPE")
	private String kidType;
	
	
	@Column(name = "KID_SEX")
	private String kidSex;
	
	@Column(name = "KID_INAME")
	private String kidIname;
	
	@Column(name = "KID_FNAME")
	private String kidFname;
	
	@Column(name = "KID_LNAME")
	private String kidLname;
	
	
	@Column(name = "PERSANAL_INFO_ID")
	private String persanalInfoId;
	
	
	@Column(name = "KID_BIRTDAY")
	private String kidBirtday;
	
	@Column(name = "KID_MOM")
	private String kidMom;
	
	@Column(name = "KID_DAD")
	private String kidDad;
	
	@Column(name = "KID_CHECK_STATUS")
	private String kidCheckStatus;
	
	@Column(name = "KID_DATE_DIE")
	private String kidDateDie;
	
	@Column(name = "KID2_CHECK_STATUS")
	private String kid2CheckStatus;
	
	@Column(name = "KID_TITLE_NAME")
	private String kidTitle;
	
	@Column(name = "KID_FIRST_NAME_DIE")
	private String kidFirstNameDie;
	
	@Column(name = "KID_LAST_NAME_DIE")
	private String kidLastNameDie;
	
	
	@Column(name = "KID_CHANGE_BIRT")
	private String kidChangeBirt;
	
	
	@Column(name = "KID_PERSANAL_ID")
	private String kidPersanalId;
	
	
	@Column(name = "KID_AGE")
	private Integer kidAge;
	
	
	
	
	
	
}
