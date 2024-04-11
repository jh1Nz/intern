package com.summitthai.cr.apprentice.deptH.dao;

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

import com.summitthai.cr.apprentice.deptH.entity.HolidayDept;
import com.summitthai.cr.apprentice.deptH.model.HolidayDeptRequest;
import com.summitthai.cr.apprentice.holiday.entity.Holiday;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.sdd.dao.jpa.AbstractJpa;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class HolidayDeptJpaImpl extends AbstractJpa<HolidayDept> implements HolidayDeptDao, Serializable {
	private static final long serialVersionUID = 1L;
	private transient EntityManager em;

	public HolidayDeptJpaImpl(EntityManager em) {
		this.setClazz(HolidayDept.class);
		this.em = em;
	}

	@Inject
	protected ApprenticeJpaService apprenticeJpaService;

	@Override
	public EntityManager getEm() {

		return (this.em != null) ? em : apprenticeJpaService.getEm();
	}

	public HolidayDeptJpaImpl() {
		setClazz(HolidayDept.class);
	}

	@Override
	public List<HolidayDept> findByReq(HolidayDeptRequest req) {
		// TODO Auto-generated method stub
		log.debug("Begin findByReq...");
		CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
		CriteriaQuery<HolidayDept> cq = cb.createQuery(HolidayDept.class);
		List<Predicate> predicates = new ArrayList<>();
		Root<HolidayDept> c = cq.from(HolidayDept.class);
		try {
			if (req != null) {
				if (!StringUtils.isNullOrEmpty(req.getHolidayID())) {
					predicates.add(cb.equal(c.get("holidayID"), req.getHolidayID()));
				}
			}

			cq.where(predicates.toArray(new Predicate[] {}));
			TypedQuery<HolidayDept> query = this.getEm().createQuery(cq);
			return query.getResultList();
		} finally {
			log.debug("End findByReq...");
		}
	}
}
