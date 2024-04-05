package com.summitthai.cr.apprentice.jpa.xsp.person.dao;

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
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.dao.XspAssignTaskJpaImpl;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.person.entity.XspPerson;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;
import com.summitthai.cr.apprentice.utils.XspUtils;
import com.summitthai.sdd.dao.jpa.AbstractJpa;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ApplicationScoped
public class XspPersonJpaImpl extends  AbstractJpa<XspPerson> 
							  implements XspPersonDao, Serializable {
	
	private static final long serialVersionUID = -683644823871020210L;
	
	// ---- for Unit Test
	private transient EntityManager em;
	
	public XspPersonJpaImpl(EntityManager em) {
		this.setClazz(XspPerson.class);
		this.em = em;
	}
	
	// --- after create new class
	@Inject
	protected ApprenticeJpaService apprenticeJpaService;
	
	@Override
	public EntityManager getEm() {
		return (this.em != null) ? em : apprenticeJpaService.getEm();
	}
	public XspPersonJpaImpl() {
        setClazz(XspPerson.class);
    }

	@Override
	public List<XspPerson> findByReq(XspPersonRequest req) {
		 log.debug("Begin findByReq...");
		 try {
			 CriteriaBuilder cb = this.getEm().getCriteriaBuilder();
	         CriteriaQuery<XspPerson> cq = cb.createQuery(XspPerson.class);
	         List<Predicate> predicates = new ArrayList<>();
	         Root<XspPerson> c = cq.from(XspPerson.class);
	         
	         if(req != null) {
	        	 if(!StringUtils.isNullOrEmpty(req.getId())) {
	        		 predicates.add(cb.equal(c.get("id"), req.getId()));
	             } 
	          }
	         cq.where(predicates.toArray(new Predicate[] {}));
	         TypedQuery<XspPerson> query = this.getEm().createQuery(cq);
	            
	         return query.getResultList();
		 } finally {
			 log.debug("End findByReq...");
		 } 
		 
	}
	
	@Override
    public List<XspPerson> findByCriteriaLikeReq(XspPersonRequest req, String suggestMode) {
     log.debug("Begin findByCriteriaLikeReq... ");

        StringBuilder sb = null;
        TypedQuery<XspPerson> query = null;
        List<XspPerson> result = new ArrayList<>();
        

        try {
         sb = new StringBuilder();
         sb.append(" SELECT m ");
         sb.append(" FROM XspPerson m ");
         sb.append(" WHERE 1 = 1 ");
         
         //if(!StringUtils.isNullOrEmpty(suggestMode) && suggestMode.equals("personNickname")) {
          if (!StringUtils.isNullOrEmpty(req.getPersonNickname())) {           
           sb.append(" AND m.personNickname LIKE :personNickname");
          }
            //}
       
         if (!StringUtils.isNullOrEmpty(req.getId())) {           
          sb.append(" AND m.id LIKE :id");
         }
         
         query = this.getEm().createQuery(sb.toString(), XspPerson.class);
         
         if (!StringUtils.isNullOrEmpty(req.getId())) {
             query.setParameter("id", req.getId());
         }
 
         if (!StringUtils.isNullOrEmpty(req.getPersonNickname())) {
             query.setParameter("personNickname", req.getPersonNickname() + "%");
         }
         
         query.setMaxResults(10000);
         result = query.getResultList();
         
         return result;
         
        } finally {
            log.debug("End findByCriteriaLikeReq... {} ");
        }
    }
	

}
