package com.summitthai.cr.apprentice.cep.tuition.dao;

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

import com.summitthai.cr.apprentice.cep.tuition.entity.CepTuition;
import com.summitthai.cr.apprentice.cep.tuition.model.CepTuitionRequest;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.sdd.dao.jpa.AbstractJpa;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CepTuitionJpaImpl extends AbstractJpa<CepTuition> implements CepTuitionDao, Serializable{
	private static final long serialVersionUID = 1L;
	private transient EntityManager em;
	
	public CepTuitionJpaImpl(EntityManager em) {
		this.setClazz(CepTuition.class);
		this.em = em;
	}

	@Inject
	protected ApprenticeJpaService apprenticeJpaService;

	@Override
	public EntityManager getEm() {

		return (this.em != null) ? em : apprenticeJpaService.getEm();
	}

	public CepTuitionJpaImpl() {
		setClazz(CepTuition.class);
	}

	@Override
	public List<CepTuition> findByReq(CepTuitionRequest req) {
		// TODO Auto-generated method stub
		log.debug("Begin findByReq...");
		CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
		CriteriaQuery<CepTuition> cq = cb.createQuery(CepTuition.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<CepTuition> c = cq.from(CepTuition.class);
		try {
			if (req != null) {
				if (!StringUtils.isNullOrEmpty(req.getPerUUID())) {
					predicates.add(cb.equal(c.get("perUUID"), req.getPerUUID()));
				}
			}

			cq.where(predicates.toArray(new Predicate[] {}));
			TypedQuery<CepTuition> query = this.getEm().createQuery(cq);
			return query.getResultList();
		} finally {
			log.debug("End findByReq...");
		}
	}
}

