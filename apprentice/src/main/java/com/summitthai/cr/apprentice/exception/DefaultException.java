package com.summitthai.cr.apprentice.exception;

import java.io.Serializable;

import com.summitthai.sdd.faces.exception.SystemException;

public class DefaultException extends SystemException implements Serializable {

    private static final long serialVersionUID = 1L;

    public DefaultException(Object... param) {
        super(param);
    }

}
