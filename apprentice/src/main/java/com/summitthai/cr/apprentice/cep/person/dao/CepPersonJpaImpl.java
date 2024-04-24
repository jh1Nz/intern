package com.summitthai.cr.apprentice.cep.person.dao;

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

import com.summitthai.cr.apprentice.cep.person.entity.CepPerson;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonRequest;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.sdd.dao.jpa.AbstractJpa;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class CepPersonJpaImpl extends AbstractJpa<CepPerson> implements CepPersonDao, Serializable {
	private static final long serialVersionUID = 1L;

	private transient EntityManager em;

	public CepPersonJpaImpl(EntityManager em) {
		this.setClazz(CepPerson.class);
		this.em = em;
	}

	@Inject
	protected ApprenticeJpaService apprenticeJpaService;

	@Override
	public EntityManager getEm() {
		return (this.em != null) ? em : apprenticeJpaService.getEm();
	}

	public CepPersonJpaImpl() {
		setClazz(CepPerson.class);
	}

	@Override
	public List<CepPerson> findByReq(CepPersonRequest req) {
		log.debug("Begin findByReq...");
		try {
			CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
			CriteriaQuery<CepPerson> cq = cb.createQuery(CepPerson.class);
			List<Predicate> predicates = new ArrayList<>();
			Root<CepPerson> c = cq.from(CepPerson.class);

			if (req != null) {
				if (!StringUtils.isNullOrEmpty(req.getPerUUID())) {
					predicates.add(cb.equal(c.get("perUUID"), req.getPerUUID()));
				}
			}
			cq.where(predicates.toArray(new Predicate[] {}));
			TypedQuery<CepPerson> query = this.getEm().createQuery(cq);

			return query.getResultList();
		} finally {
			log.debug("End findByReq...");
		}

	}
}
