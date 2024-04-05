package com.summitthai.cr.apprentice.cep.model;
import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@EqualsAndHashCode()
@ToString(
		callSuper = true,
		includeFieldNames = true,
		doNotUseGetters = false
		)

public class CepRequest {
	
	private String Mom;
	private String Dad;
	private String kidFname;
	private String kidLname;
	private String Sex;
	private Date Birt;
	private boolean checkKidStatus;
	private boolean checkKid2Status;
	private String id;
	private String kidInname;
	private String fname;
	private int kidAge;
	
	
	
	
	
	
	
	

}
