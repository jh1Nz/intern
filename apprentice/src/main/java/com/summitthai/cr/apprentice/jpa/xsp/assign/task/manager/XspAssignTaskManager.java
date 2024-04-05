package com.summitthai.cr.apprentice.jpa.xsp.assign.task.manager;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.persistence.jpa.rs.exceptions.ErrorResponse;

import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.dao.XspAssignTaskDao;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.dto.XspAssignTaskDto;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.entity.XspAssignTask;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskRequest;
import com.summitthai.cr.apprentice.jpa.xsp.assign.task.model.XspAssignTaskResponse;
import com.summitthai.sdd.sys.util.TransformUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@Data
public class XspAssignTaskManager implements XspAssignTaskManageable{
	
	private static final long serialVersionUID = 2734302709208647675L;
	
	@Inject
    private ApprenticeJpaService db;
	
	@Inject
    private XspAssignTaskDao xspAssignTaskDao;
	
	private XspAssignTaskDto xspAssignTaskDto;
	
	public XspAssignTaskManager() {
		this.xspAssignTaskDto = new XspAssignTaskDto();
    }
	
	public XspAssignTaskManager(ApprenticeJpaService db, XspAssignTaskDao xspAssignTaskDao) {
        this.setDb(db);
       
    }
	
	@Override
	public XspAssignTaskResponse findByReq(XspAssignTaskRequest req) {
		log.debug("Begin XspAssignTaskResponse findByReq...");
		
		XspAssignTaskResponse  response    = null;
        XspAssignTaskRequest   request     = null;
        //ErrorResponse             error       = null;
        List<XspAssignTask>    entityList  = new ArrayList<>();
        
        
        try {
        	entityList = this.xspAssignTaskDao.findByReq(req);
            response = XspAssignTaskResponse.builder().dataRequestList(new ArrayList<>()).build();
            
            for (XspAssignTask data : entityList) {
                request = this.xspAssignTaskDto.entityToReq(data);
                request.setStringToDate();
                response.getDataRequestList().add(request);
            }
            
            response.setStatus("SUCCESS");
            return response;
            
         }catch (Exception e) {
        	 log.error("Exception findByReq : {}", e);
        	 response.setError("ERROR");
             return response;
         } finally {
        	 log.debug("End XspAssignTaskResponse findByReq...");
		}

	}
	
    @Override
    public XspAssignTaskResponse insert(XspAssignTaskRequest req) {
        log.debug("Begin insert....");
        XspAssignTaskResponse  res     = null;
        XspAssignTask       entity   = new XspAssignTask();
        
        try {

            this.db.begin();

            entity = this.xspAssignTaskDto.reqToEntity(entity, req);
            
            this.xspAssignTaskDao.create(entity);
            this.db.commit();
            res = XspAssignTaskResponse
                    .builder()
                    .status                     ("SUCCESS")
                    .build();
            return res;
        } catch (Exception e) {
            this.db.rollback();
            log.error("Exception : {}", e);
            res.setError("ERROR");
            return res;
        } finally {
            log.debug("End insert....");
            res     = null;
        }
    }
    
    @Override
    public XspAssignTaskResponse update(XspAssignTaskRequest req) {
    	log.debug("Begin update....");
    	XspAssignTaskResponse res = null;
    	
    	try {
    		this.db.begin();
    		
    		XspAssignTask newEntity = this.xspAssignTaskDao.find(req.getId());
    		XspAssignTask oldEntity = new XspAssignTask();
    		TransformUtils.copyProperties(oldEntity, newEntity);
    		
    		newEntity = this.xspAssignTaskDto.reqToEntity(newEntity, req);
    		xspAssignTaskDao.update(newEntity);
			
    		this.db.commit();
    		res = XspAssignTaskResponse
                    .builder()
                    .status                     ("SUCCESS")
                    .build();
            return res;
		} catch (Exception e) {
			this.db.rollback();
            log.error("Exception : {}", e);
            res.setError("ERROR");
            return res;
		}finally {
			log.debug("End update....");
            res     = null;
		}
    }
    
    @Override
    public XspAssignTaskResponse delete(XspAssignTaskRequest req) {
    	log.debug("Begin delete....");
    	XspAssignTaskResponse res = null;
    	XspAssignTask entity = new XspAssignTask();
    	
    	try {
    		this.db.begin();
    		
    		entity = this.xspAssignTaskDao.find(req.getId());
    		
    		if (entity != null) {
				this.xspAssignTaskDao.delete(entity);
			}
    		
    		this.db.commit();
    		res = XspAssignTaskResponse
                    .builder()
                    .status                     ("SUCCESS")
                    .build();
            return res;
		} catch (Exception e) {
			log.error("Exception : {}", e);
            res.setError("ERROR");
            return res;
		}finally {
			log.debug("End delete....");
            res     = null;
		}
    }  	
    
}
