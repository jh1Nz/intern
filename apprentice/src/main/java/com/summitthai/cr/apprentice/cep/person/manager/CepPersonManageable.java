package com.summitthai.cr.apprentice.cep.person.manager;

import java.io.Serializable;

import com.summitthai.cr.apprentice.cep.person.model.CepPersonRequest;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonResponse;

public interface CepPersonManageable extends Serializable{
	public CepPersonResponse findByReq(CepPersonRequest req);
	
	public CepPersonResponse insert(CepPersonRequest req);
	
	public CepPersonResponse update(CepPersonRequest req);
	
	public CepPersonResponse delete(CepPersonRequest req);
	
	public CepPersonResponse search(CepPersonRequest req);
}
