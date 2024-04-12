package com.summitthai.cr.apprentice.holiday.manager;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.summitthai.cr.apprentice.deptH.dao.HolidayDeptDao;
import com.summitthai.cr.apprentice.deptH.dto.HolidayDeptDto;
import com.summitthai.cr.apprentice.deptH.entity.HolidayDept;
import com.summitthai.cr.apprentice.deptH.model.HolidayDeptRequest;
import com.summitthai.cr.apprentice.holiday.dao.HolidayDao;
import com.summitthai.cr.apprentice.holiday.dto.HolidayDto;
import com.summitthai.cr.apprentice.holiday.entity.Holiday;
import com.summitthai.cr.apprentice.holiday.model.HolidayRequest;
import com.summitthai.cr.apprentice.holiday.model.HolidayResponse;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.jpa.xsp.person.entity.XspPerson;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonRequest;
import com.summitthai.cr.apprentice.jpa.xsp.person.model.XspPersonResponse;
import com.summitthai.cr.apprentice.kids.entity.Kidinfo;
import com.summitthai.cr.apprentice.kids.model.DataKidRequest;
import com.summitthai.cr.apprentice.persanal.entity.PersanalInfo;
import com.summitthai.cr.apprentice.persanal.model.PersanalRequest;
import com.summitthai.cr.apprentice.persanal.model.PersanalResponse;
import com.summitthai.cr.apprentice.status.SimpleStatus;
import com.summitthai.sdd.sys.util.TransformUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@Data
public class HolidayManager implements HolidayManageable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ApprenticeJpaService db;

	@Inject
	private HolidayDao holidayDao;

	@Inject
	private HolidayDeptDao holidayDeptDao;

	private HolidayDto holidayDto;
	private HolidayDeptDto holidayDeptDto;

	public HolidayManager() {
		this.holidayDto = new HolidayDto();
		this.holidayDeptDto = new HolidayDeptDto();
	}

	@Override
	public HolidayResponse findByReq(HolidayRequest req) {
		log.debug("Begin  findByReq...");

		HolidayResponse response = null;
		HolidayRequest request = null;
		// ErrorResponse error = null;
		List<Holiday> entityList = new ArrayList<>();

		try {
			entityList = this.holidayDao.findByReq(req);
			response = HolidayResponse.builder().dataRequestList(new ArrayList<>()).build();

			for (Holiday data : entityList) {
				request = this.holidayDto.entityToReq(data);
				response.getDataRequestList().add(request);
			}

			response.setStatus(SimpleStatus.SUCCESS);
			return response;

		} catch (Exception e) {
			log.error("Exception findByReq : {}", e);
			response.setError(SimpleStatus.ERROR);
			return response;
		} finally {
			log.debug("End  findByReq...");
		}
	}

	// For insert Dept
	private void insertDept(HolidayRequest req) {
		log.debug("Begin insertDept");
		try {
			for (HolidayDeptRequest data : req.getDeptList()) {
				HolidayDept entity = new HolidayDept();
				data.setHolidayID(req.getHolidayID());
				this.holidayDeptDao.create(this.holidayDeptDto.reqToEntity(entity, data));
			}
		} catch (Exception e) {

		}
	}

	@Override
	public HolidayResponse insert(HolidayRequest req) {
		log.debug("Begin Insert Manager...");
		HolidayResponse res = null;
		Holiday entity = new Holiday();

		try {

			this.db.begin();
			insertDept(req);
			entity = this.holidayDto.reqToEntity(entity, req);
			this.holidayDao.create(entity);
			this.db.commit();
			res = HolidayResponse.builder().status(SimpleStatus.SUCCESS).build();
			return res;
		} catch (Exception e) {
			this.db.rollback();
			log.error("Exception Manager insert", e);
			res = HolidayResponse.builder().status(SimpleStatus.FAIL).build();
			return res;
		} finally {

			res = null;
			entity = null;
			log.debug("End Manager insert");
		}

	}

	@Override
	public HolidayResponse update(HolidayRequest req) {
		log.debug("Begin update ");
		HolidayResponse res = null;
		try {
			this.db.begin();

			Holiday newEntity = this.holidayDao.find(req.getHolidayID());
			log.debug("test {}", newEntity);
			Holiday oldEntity = new Holiday();
			TransformUtils.copyProperties(oldEntity, newEntity);

			newEntity = this.holidayDto.reqToEntity(newEntity, req);
			
			holidayDao.update(newEntity);
			deleteDept(req);	
			this.db.flush();
			insertDept(req);
			
			this.db.commit();
			res = HolidayResponse.builder().status(SimpleStatus.SUCCESS).build();
			return res;

		} catch (Exception e) {
			this.db.rollback();
			log.error("Exception : {}", e);
			res.setError(SimpleStatus.ERROR);
			return res;
		} finally {
			log.debug("End update....");
			res = null;
		}
	}
	
	@Override
	public HolidayResponse delete(HolidayRequest req) {
		log.debug("Begin delete....");
		HolidayResponse res = null;
		Holiday entity= new Holiday();
 
    	
    	try {
    		this.db.begin();
    		entity = this.holidayDao.find(req.getHolidayID());
    		if (entity != null) {
				this.holidayDao.delete(entity);
				deleteDept(req);
				
			}
    		this.db.commit();
    		res = HolidayResponse.builder().status(SimpleStatus.SUCCESS).build();
    		return res;
    	}catch (Exception e) {
			log.error("Exception : {}", e);
			res.setError(SimpleStatus.ERROR);
            return res;
		}finally {
			log.debug("End delete....");
			res     = null;
		}
    	
	}
	
	private void deleteDept(HolidayRequest req) {
		 List<HolidayDeptRequest> deptList = req.getDeptList();
		    if (deptList == null) {
		        log.warn("No found in the request.");
		        return;
		    }

		    for (HolidayDeptRequest data : deptList) {
		        String holId = data.getHolidayDeptID(); 
		        if (holId != null) {
		            try {
		                HolidayDept entityDept = this.holidayDeptDao.find(holId);
		                if (entityDept != null) {
		                    this.holidayDeptDao.delete(entityDept);
		                }
		            } catch (Exception e) {
		                log.error("Exception while deleting  with ID {}: {}", holId, e.getMessage());
		            }
		        } else {
		            log.warn("Null found in the request data.");
		        }
		    }
		
	}
	

}
