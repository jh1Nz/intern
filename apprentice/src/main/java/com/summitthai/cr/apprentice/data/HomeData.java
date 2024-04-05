package com.summitthai.cr.apprentice.data;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HomeData implements Serializable {

	private static final long serialVersionUID = -4026138150445172519L;
	
	private String code;
	
	private String name;
	
	private String category;
	
	private String quantity;

}
