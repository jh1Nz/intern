package com.summitthai.cr.apprentice.jpa.xsp.assign.task.dao;

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
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.utils.XspUtils;
import com.summitthai.sdd.dao.jpa.AbstractJpa;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class XspAssignTaskJpaImpl extends AbstractJpa<XspAssignTask> implements XspAssignTaskDao, Serializable {

	private static final long serialVersionUID = -683644823871020210L;

	private transient EntityManager em;

	public XspAssignTaskJpaImpl(EntityManager em) {
		this.setClazz(XspAssignTask.class);
		this.em = em;
	}

	@Inject
	protected ApprenticeJpaService apprenticeJpaService;

	@Override
	public EntityManager getEm() {

		return (this.em != null) ? em : apprenticeJpaService.getEm();
	}

	public XspAssignTaskJpaImpl() {
		setClazz(XspAssignTask.class);
	}

	@Override
	public List<XspAssignTask> findByReq(XspAssignTaskRequest req) {
		log.debug("Begin findByReq...");
		try {
			CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
			CriteriaQuery<XspAssignTask> cq = cb.createQuery(XspAssignTask.class);
			List<Predicate> predicates = new ArrayList<>();
			Root<XspAssignTask> c = cq.from(XspAssignTask.class);

			if (req != null) {
				if (!StringUtils.isNullOrEmpty(req.getId())) {
					predicates.add(cb.equal(c.get("id"), req.getId()));
				}

				if (!StringUtils.isNullOrEmpty(req.getStDate()) && !StringUtils.isNullOrEmpty(req.getFnDate())) {
					predicates.add(cb.between(c.get("assignDatetime"), req.getStDate(), req.getFnDate()));
				}

				if (req.getXspPersonAssignRequest() != null
						&& !StringUtils.isNullOrEmpty(req.getXspPersonAssignRequest().getId())) {
					predicates.add(cb.equal(c.get("actorId"), req.getXspPersonAssignRequest().getId()));
				}
				if (req.getTaskStatusList() != null && !req.getTaskStatusList().isEmpty()) {
					String dataA = "";
					String dataB = "";
					String dataC = "";
					String dataD = "";

					for (String data : req.getTaskStatusList()) {
						if (data.equals("A")) {
							dataA = data;
						}
						if (data.equals("P")) {
							dataB = data;
						}
						if (data.equals("S")) {
							dataC = data;
						}
						if (data.equals("F")) {
							dataD = data;
						}
					}

					predicates.add(cb.or(cb.equal(c.get("taskStatus"), dataA), cb.equal(c.get("taskStatus"), dataB),
							cb.equal(c.get("taskStatus"), dataC), cb.equal(c.get("taskStatus"), dataD)));
				}
			}
			cq.where(predicates.toArray(new Predicate[] {}));
			TypedQuery<XspAssignTask> query = this.getEm().createQuery(cq);

			return query.getResultList();
		} finally {
			log.debug("End findByReq...");
		}

	}

}
