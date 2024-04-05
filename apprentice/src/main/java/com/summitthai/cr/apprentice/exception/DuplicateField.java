package com.summitthai.cr.apprentice.exception;

import java.io.Serializable;

import com.summitthai.sdd.faces.exception.SystemException;

public class DuplicateField extends SystemException implements Serializable {

	private static final long serialVersionUID = 1L;

	public DuplicateField(Object... param) {
		super(param);
	}

}
