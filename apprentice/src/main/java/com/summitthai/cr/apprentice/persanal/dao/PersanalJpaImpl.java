package com.summitthai.cr.apprentice.persanal.dao;

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

import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.persanal.entity.PersanalInfo;
import com.summitthai.cr.apprentice.persanal.model.PersanalRequest;
import com.summitthai.sdd.dao.jpa.AbstractJpa;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class PersanalJpaImpl extends AbstractJpa<PersanalInfo> implements PersanalDao, Serializable {

	private static final long serialVersionUID = -683644823871020210L;

	private transient EntityManager em;

	public PersanalJpaImpl(EntityManager em) {
		this.setClazz(PersanalInfo.class);
		this.em = em;
	}

	@Inject
	protected ApprenticeJpaService apprenticeJpaService;

	@Override
	public EntityManager getEm() {

		return (this.em != null) ? em : apprenticeJpaService.getEm();
	}

	public PersanalJpaImpl() {
		setClazz(PersanalInfo.class);
	}


	@Override
	public List<PersanalInfo> findByReq(PersanalRequest req) {
		log.debug("Begin findByReq...");
		CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
		CriteriaQuery<PersanalInfo> cq = cb.createQuery(PersanalInfo.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<PersanalInfo> c = cq.from(PersanalInfo.class);

		try {
			if (req != null) {
				if (!StringUtils.isNullOrEmpty(req.getPersanalInfoId())) {
					predicates.add(cb.equal(c.get("persanalInfoId"), req.getPersanalInfoId()));
				}
				if (!StringUtils.isNullOrEmpty(req.getPerId())) {
					predicates.add(cb.equal(c.get("perId"), req.getPerId()));
				}
			}
			
			cq.where(predicates.toArray(new Predicate[] {}));
			TypedQuery<PersanalInfo> query = this.getEm().createQuery(cq);
			return query.getResultList();
		} finally {
			log.debug("End findByReq...");
		}

	}

}
