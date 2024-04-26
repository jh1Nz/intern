package com.summitthai.cr.apprentice.cep.spouse.manager;

import java.io.Serializable;

import com.summitthai.cr.apprentice.cep.spouse.model.CepSpouseRequest;
import com.summitthai.cr.apprentice.cep.spouse.model.CepSpouseResponse;

public interface CepSpouseManageable extends Serializable{
	public CepSpouseResponse findByReq(CepSpouseRequest req);
}
