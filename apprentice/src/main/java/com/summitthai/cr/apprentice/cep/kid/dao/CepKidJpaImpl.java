package com.summitthai.cr.apprentice.cep.kid.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.summitthai.cr.apprentice.cep.kid.entity.CepKid;
import com.summitthai.cr.apprentice.cep.kid.model.CepKidRequest;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.sdd.dao.jpa.AbstractJpa;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CepKidJpaImpl extends AbstractJpa<CepKid> implements CepKidDao,Serializable{
	private static final long serialVersionUID = 1L;
	private transient EntityManager em;
	
	public CepKidJpaImpl(EntityManager em) {
		this.setClazz(CepKid.class);
		this.em = em;
	}
	@Inject
	protected ApprenticeJpaService apprenticeJpaService;
	
	
	@Override
	public EntityManager getEm() {

		return (this.em != null) ? em : apprenticeJpaService.getEm();
	}
	public CepKidJpaImpl() {
		setClazz(CepKid.class);
	}
	
	@Override
	public List<CepKid> findByReq(CepKidRequest req) {
		// TODO Auto-generated method stub
		log.debug("Begin findByReq...");
		CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
		CriteriaQuery<CepKid> cq = cb.createQuery(CepKid.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<CepKid> c = cq.from(CepKid.class);
		try {
			if (req != null) {
				if (!StringUtils.isNullOrEmpty(req.getPerUUID())) {
					predicates.add(cb.equal(c.get("perUUID"), req.getPerUUID()));
				}
			}

			cq.where(predicates.toArray(new Predicate[] {}));
			TypedQuery<CepKid> query = this.getEm().createQuery(cq);
			return query.getResultList();
		} finally {
			log.debug("End findByReq...");
		}
	}

}
