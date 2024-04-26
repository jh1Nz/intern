package com.summitthai.cr.apprentice.cep.spouse.dao;

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

import com.summitthai.cr.apprentice.cep.spouse.entity.CepSpouse;
import com.summitthai.cr.apprentice.cep.spouse.model.CepSpouseRequest;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.sdd.dao.jpa.AbstractJpa;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CepSpouseJpaImpl extends AbstractJpa<CepSpouse> implements CepSpouseDao, Serializable {
	private static final long serialVersionUID = 1L;
	private transient EntityManager em;

	public CepSpouseJpaImpl(EntityManager em) {
		this.setClazz(CepSpouse.class);
		this.em = em;
	}

	@Inject
	protected ApprenticeJpaService apprenticeJpaService;

	@Override
	public EntityManager getEm() {

		return (this.em != null) ? em : apprenticeJpaService.getEm();
	}

	public CepSpouseJpaImpl() {
		setClazz(CepSpouse.class);
	}

	@Override
	public List<CepSpouse> findByReq(CepSpouseRequest req) {
		// TODO Auto-generated method stub
		log.debug("Begin findByReq...");
		CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
		CriteriaQuery<CepSpouse> cq = cb.createQuery(CepSpouse.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<CepSpouse> c = cq.from(CepSpouse.class);
		try {
			if (req != null) {
				if (!StringUtils.isNullOrEmpty(req.getPerUUID())) {
					predicates.add(cb.equal(c.get("perUUID"), req.getPerUUID()));
				}
			}

			cq.where(predicates.toArray(new Predicate[] {}));
			TypedQuery<CepSpouse> query = this.getEm().createQuery(cq);
			return query.getResultList();
		} finally {
			log.debug("End findByReq...");
		}
	}
}
