package com.summitthai.cr.apprentice.cep.tuition.manager;

import java.io.Serializable;

import com.summitthai.cr.apprentice.cep.tuition.model.CepTuitionRequest;
import com.summitthai.cr.apprentice.cep.tuition.model.CepTuitionResponse;

public interface CepTuitionManageable extends Serializable{
	public CepTuitionResponse findByReq(CepTuitionRequest req);
}
