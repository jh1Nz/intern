package com.summitthai.cr.apprentice.history.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name= "education_history")
@Data
public class History implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "EDUCATION_HISTORY_ID")
	private String educationHistoryId;
	
	@Column(name = "KID_INAME")
	private String kidIname;
	
	@Column(name = "KID_FNAME")
	private String kidFname;
	
	@Column(name = "KID_LNAME")
	private String kidLname;
	
	
	@Column(name = "EDU_YEAR")
	private String eduYear ;
	
	@Column(name = "EDU_LEVEL")
	private String eduLevel ;
	
	@Column(name = "EDU_START")
	private String eduStart ;
	
	@Column(name = "EDU_PLACE_PART")
	private String eduPlacePart ;
	
	@Column(name = "EDU_PLACE_TYPE")
	private String eduPlaceType ;
	
	@Column(name = "EDU_GRAN")
	private String eduGran ;
	
	@Column(name = "EDU_BRANCH")
	private String eduBranch ;
	
	@Column(name = "EDU_PLACE")
	private String eduPlace ;
	
	@Column(name = "EDU_PROVINE")
	private String eduProvine ;
	
	@Column(name = "EDU_DISTRICT")
	private String eduDistrict ;
	
	@Column(name = "EDU_SUSDISTRICT")
	private String eduSusdistrict ;
	
	@Column(name = "EDU_MARK")
	private String eduMark ;
	
	
	@Column(name = "PERSANAL_INFO_ID")
	private String persanalInfoId;
	
	
	
}
