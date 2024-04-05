package com.summitthai.cr.apprentice.kids.dao;

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
import com.summitthai.cr.apprentice.kids.entity.Kidinfo;
import com.summitthai.cr.apprentice.kids.model.DataKidRequest;
import com.summitthai.sdd.dao.jpa.AbstractJpa;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class KidinfoJpaImpl extends AbstractJpa<Kidinfo> implements KidinfoDao, Serializable {

	private static final long serialVersionUID = -683644823871020210L;

	private transient EntityManager em;

	public KidinfoJpaImpl(EntityManager em) {
		this.setClazz(Kidinfo.class);
		this.em = em;
	}

	@Inject
	protected ApprenticeJpaService apprenticeJpaService;

	@Override
	public EntityManager getEm() {

		return (this.em != null) ? em : apprenticeJpaService.getEm();
	}

	public KidinfoJpaImpl() {
		setClazz(Kidinfo.class);
	}


	@Override
	public List<Kidinfo> findByReq(DataKidRequest req) {
		log.debug("Begin findByReq...");
		CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
		CriteriaQuery<Kidinfo> cq = cb.createQuery(Kidinfo.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<Kidinfo> c = cq.from(Kidinfo.class);

		try {
			if (req != null) {
				if (!StringUtils.isNullOrEmpty(req.getPersanalInfoId())) {
					predicates.add(cb.equal(c.get("persanalInfoId"), req.getPersanalInfoId()));
				}
			}
			
			cq.where(predicates.toArray(new Predicate[] {}));
			TypedQuery<Kidinfo> query = this.getEm().createQuery(cq);
			return query.getResultList();
		} finally {
			log.debug("End findByReq...");
		}

	}

}
