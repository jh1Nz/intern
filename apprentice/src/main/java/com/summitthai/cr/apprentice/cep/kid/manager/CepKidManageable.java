package com.summitthai.cr.apprentice.cep.kid.manager;

import java.io.Serializable;

import com.summitthai.cr.apprentice.cep.kid.model.CepKidRequest;
import com.summitthai.cr.apprentice.cep.kid.model.CepKidResponse;

public interface CepKidManageable extends Serializable{
	public CepKidResponse findByReq(CepKidRequest req);
}
