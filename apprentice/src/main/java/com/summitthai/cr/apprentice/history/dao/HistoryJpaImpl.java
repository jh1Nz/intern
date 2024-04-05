package com.summitthai.cr.apprentice.history.dao;

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

import com.summitthai.cr.apprentice.history.entity.History;
import com.summitthai.cr.apprentice.history.model.DataKidEducationRequest;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.sdd.dao.jpa.AbstractJpa;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class HistoryJpaImpl extends AbstractJpa<History> implements HistoryDao, Serializable {

	private static final long serialVersionUID = -683644823871020210L;

	private transient EntityManager em;

	public HistoryJpaImpl(EntityManager em) {
		this.setClazz(History.class);
		this.em = em;
	}

	@Inject
	protected ApprenticeJpaService apprenticeJpaService;

	@Override
	public EntityManager getEm() {

		return (this.em != null) ? em : apprenticeJpaService.getEm();
	}

	public HistoryJpaImpl() {
		setClazz(History.class);
	}


	@Override
	public List<History> findByReq(DataKidEducationRequest req) {
		log.debug("Begin findByReq...");
		CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
		CriteriaQuery<History> cq = cb.createQuery(History.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<History> c = cq.from(History.class);

		try {
			if (req != null) {
				if (!StringUtils.isNullOrEmpty(req.getPersanalInfoId())) {
					predicates.add(cb.equal(c.get("persanalInfoId"), req.getPersanalInfoId()));
				}
			}
			
			cq.where(predicates.toArray(new Predicate[] {}));
			TypedQuery<History> query = this.getEm().createQuery(cq);
			return query.getResultList();
		} finally {
			log.debug("End findByReq...");
		}

	}

}
