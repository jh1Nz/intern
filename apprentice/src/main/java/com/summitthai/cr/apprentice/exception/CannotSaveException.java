package com.summitthai.cr.apprentice.exception;

import java.io.Serializable;

import com.summitthai.sdd.faces.exception.SystemException;

public class CannotSaveException extends SystemException implements Serializable {

    private static final long serialVersionUID = 1L;

    public CannotSaveException(Object... param) {
        super(param);
    }

}
