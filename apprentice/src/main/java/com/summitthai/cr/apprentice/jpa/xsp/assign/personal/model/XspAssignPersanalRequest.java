package com.summitthai.cr.apprentice.jpa.xsp.assign.personal.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;
import com.summitthai.cr.apprentice.jpa.xsp.project.model.XspProjectRequest;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenRequest;
import com.summitthai.cr.apprentice.utils.XspUtils;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@EqualsAndHashCode(
//		callSuper = true,
//		doNotUseGetters = false,
//		of = {"id"}
		)
@ToString(
		callSuper = true,
		includeFieldNames = true,
		doNotUseGetters = false
		)
@XmlRootElement(name = "XspAssignTaskRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class XspAssignPersanalRequest {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "PersanalId")
	private String PersanalId;

	@XmlElement(name = "approveFlage")
	private String approveFlage;
	
	
	@XmlElement(name = "kidsName")
	private String kidsName;
	
	@XmlElement(name = "perLastName")
	private String perLastName;
	
	@XmlElement(name = "semester")
	private String semester;
	
	@XmlElement(name = "rank")
	private int rank;
	
	@XmlElement(name = "nRank")
	private String nRank;
	
	@XmlElement(name = "sRank")
	private String sRank;
	
	@XmlElement(name = "sChName")
	private String sChName;
	
	@XmlElement(name = "pSch")
	private String pSch;
	
	@XmlElement(name = "line")
	private String line;
	
	
	
	
}
