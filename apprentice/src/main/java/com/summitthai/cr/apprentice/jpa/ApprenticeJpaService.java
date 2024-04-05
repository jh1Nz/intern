package com.summitthai.cr.apprentice.jpa;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.summitthai.sdd.dao.jpa.JpaService;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
@ApplicationScoped
public class ApprenticeJpaService extends JpaService{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ---- for Unit Test

	public ApprenticeJpaService(){
	}
	
	public ApprenticeJpaService(EntityManager em){
		this.em = em;
	}
	
	// --- after create new class
	@Inject
    @ApprenticeDb
    private EntityManager em;

}
