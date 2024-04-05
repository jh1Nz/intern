package com.summitthai.cr.apprentice.datetime.model;
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

public class DateTimeRequest {
	private static final long serialVersionUID = 1L;
	private Date dateUi;
	private String nameDay;
	private String id;

}
