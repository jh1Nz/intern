package com.summitthai.cr.apprentice.jpa.xsp.screen.dao;

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
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.dao.XspAssignTaskDao;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.dao.XspAssignTaskJpaImpl;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.person.entity.XspPerson;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;
import com.summitthai.cr.apprentice.jpa.xsp.screen.entity.XspScreen;
import com.summitthai.cr.apprentice.jpa.xsp.screen.model.XspScreenRequest;
import com.summitthai.cr.apprentice.utils.XspUtils;
import com.summitthai.sdd.dao.jpa.AbstractJpa;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class XspScreenJpaImpl extends AbstractJpa<XspScreen> 
							  implements XspScreenDao, Serializable{
	
	private static final long serialVersionUID = -683644823871020210L;
	
	private transient EntityManager em;
	
	public XspScreenJpaImpl(EntityManager em) {
		this.setClazz(XspScreen.class);
		this.em = em;
	}
	
	@Inject
	protected ApprenticeJpaService apprenticeJpaService;

	@Override
	public EntityManager getEm() {
		
		return (this.em != null) ? em : apprenticeJpaService.getEm();
	}
	public XspScreenJpaImpl() {
        setClazz(XspScreen.class);
    }
	
	@Override
	public List<XspScreen> findByReq(XspScreenRequest req) {
		 log.debug("Begin findByReq...");
		 try {
			 CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
	         CriteriaQuery<XspScreen> cq = cb.createQuery(XspScreen.class);
	         List<Predicate> predicates = new ArrayList<>();
	         Root<XspScreen> c = cq.from(XspScreen.class);
	         
	         if(req != null) {
	        	 if(!StringUtils.isNullOrEmpty(req.getId())) {
	        		 predicates.add(cb.equal(c.get("id"), req.getId()));
	             }
	          }
	         cq.where(predicates.toArray(new Predicate[] {}));
	         TypedQuery<XspScreen> query = this.getEm().createQuery(cq);
	         
	         return query.getResultList();
		} finally {
			 log.debug("End findByReq...");
		}
	}
	
	@Override
    public List<XspScreen> findByCriteriaLikeReq(XspScreenRequest req, String suggestMode) {
     log.debug("Begin findByCriteriaLikeReq... ");

        StringBuilder sb = null;
        TypedQuery<XspScreen> query = null;
        List<XspScreen> result = new ArrayList<>();
        

        try {
         sb = new StringBuilder();
         sb.append(" SELECT m ");
         sb.append(" FROM XspScreen m ");
         sb.append(" WHERE 1 = 1 ");
         
         //if(!StringUtils.isNullOrEmpty(suggestMode) && suggestMode.equals("personNickname")) {
          if (!StringUtils.isNullOrEmpty(req.getId())) {           
           sb.append(" AND m.id LIKE :id");
          }
            //}
       
         if (!StringUtils.isNullOrEmpty(req.getId())) {           
          sb.append(" AND m.id LIKE :id");
         }
         
         query = this.getEm().createQuery(sb.toString(), XspScreen.class);
         
         if (!StringUtils.isNullOrEmpty(req.getId())) {
             query.setParameter("id", req.getId());
         }
 
         if (!StringUtils.isNullOrEmpty(req.getId())) {
             query.setParameter("id", req.getId() + "%");
         }
         
         query.setMaxResults(10000);
         result = query.getResultList();
         
         return result;
         
        } finally {
            log.debug("End findByCriteriaLikeReq... {} ");
        }
    }
}
