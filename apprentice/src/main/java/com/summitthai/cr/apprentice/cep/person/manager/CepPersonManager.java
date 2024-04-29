package com.summitthai.cr.apprentice.cep.person.manager;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.summitthai.cr.apprentice.cep.kid.dao.CepKidDao;
import com.summitthai.cr.apprentice.cep.kid.dto.CepKidDto;
import com.summitthai.cr.apprentice.cep.kid.entity.CepKid;
import com.summitthai.cr.apprentice.cep.kid.model.CepKidRequest;
import com.summitthai.cr.apprentice.cep.person.dao.CepPersonDao;
import com.summitthai.cr.apprentice.cep.person.dto.CepPersonDto;
import com.summitthai.cr.apprentice.cep.person.entity.CepPerson;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonRequest;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonResponse;
import com.summitthai.cr.apprentice.cep.spouse.dao.CepSpouseDao;
import com.summitthai.cr.apprentice.cep.spouse.dto.CepSpouseDto;
import com.summitthai.cr.apprentice.cep.spouse.entity.CepSpouse;
import com.summitthai.cr.apprentice.cep.spouse.model.CepSpouseRequest;
import com.summitthai.cr.apprentice.cep.tuition.dao.CepTuitionDao;
import com.summitthai.cr.apprentice.cep.tuition.dto.CepTuitionDto;
import com.summitthai.cr.apprentice.cep.tuition.entity.CepTuition;
import com.summitthai.cr.apprentice.cep.tuition.model.CepTuitionRequest;
import com.summitthai.cr.apprentice.holiday.entity.Holiday;
import com.summitthai.cr.apprentice.holiday.model.HolidayResponse;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.status.SimpleStatus;
import com.summitthai.sdd.sys.util.DateUtils;
import com.summitthai.sdd.sys.util.StringUtils;
import com.summitthai.sdd.sys.util.TransformUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@Data
public class CepPersonManager implements CepPersonManageable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ApprenticeJpaService db;

	@Inject
	private CepPersonDao cepPersonDao;
	
	@Inject
	private CepSpouseDao cepSpouseDao;

	@Inject
	private CepTuitionDao cepTuitionDao;
	
	@Inject 
	private CepKidDao cepKidDao;
	
	private CepKidDto cepKidDto;
	
	private CepSpouseDto cepSpouseDto;

	private CepTuitionDto cepTuitionDto;

	private CepPersonDto cepPersonDto;

	public CepPersonManager() {
		this.cepPersonDto = new CepPersonDto();
		this.cepTuitionDto = new CepTuitionDto();
		this.cepSpouseDto = new CepSpouseDto();
		this.cepKidDto = new CepKidDto();
	}

	public CepPersonManager(ApprenticeJpaService db, CepPersonDao cepPersonDao) {
		this.setDb(db);
	}

	@Override
	public CepPersonResponse findByReq(CepPersonRequest req) {
		log.debug("Begin  findByReq...");

		CepPersonResponse response = null;
		CepPersonRequest request = null;
		// ErrorResponse error = null;
		List<CepPerson> entityList = new ArrayList<>();

		try {
			entityList = this.cepPersonDao.findByReq(req);
			response = CepPersonResponse.builder().dataRequestList(new ArrayList<>()).build();

			for (CepPerson data : entityList) {
				request = this.cepPersonDto.entityToReq(data);
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
	private void deleteSpo(CepPersonRequest req) {
		 List<CepSpouseRequest> spoList = req.getSpouseList();
		    if (spoList == null) {
		        log.warn("No found in the request.");
		        return;
		    }

		    for (CepSpouseRequest data : spoList) {
		        String spoId = data.getSpouseUUID(); 
		        if (spoId != null) {
		            try {
		                CepSpouse entitySpo = this.cepSpouseDao.find(spoId);
		                if (entitySpo != null) {
		                	this.cepSpouseDao.delete(entitySpo);
		                }
		            } catch (Exception e) {
		                log.error("Exception while deleting  with ID {}: {}", spoId, e.getMessage());
		            }
		        } else {
		            log.warn("Null found in the request data.");
		        }
		    }
		
	}
	private void deleteTui(CepPersonRequest req) {
		 List<CepTuitionRequest> tuiList = req.getTuitionList();
		    if (tuiList == null) {
		        log.warn("No found in the request.");
		        return;
		    }

		    for (CepTuitionRequest data : tuiList) {
		        String tuiId = data.getTuitionUUID(); 
		        if (tuiId != null) {
		            try {
		                CepTuition entityTui = this.cepTuitionDao.find(tuiId);
		                if (entityTui != null) {
		                	this.cepTuitionDao.delete(entityTui);
		                }
		            } catch (Exception e) {
		                log.error("Exception while deleting  with ID {}: {}", tuiId, e.getMessage());
		            }
		        } else {
		            log.warn("Null found in the request data.");
		        }
		    }
		
	}
	private void deleteKid(CepPersonRequest req) {
		 List<CepKidRequest> kidList = req.getKidList();
		    if (kidList == null) {
		        log.warn("No found in the request.");
		        return;
		    }

		    for (CepKidRequest data : kidList) {
		        String kidId = data.getKidUUID(); 
		        if (kidId != null) {
		            try {
		                CepKid entityKid = this.cepKidDao.find(kidId);
		                if (entityKid != null) {
		                	this.cepKidDao.delete(entityKid);
		                }
		            } catch (Exception e) {
		                log.error("Exception while deleting  with ID {}: {}", kidId, e.getMessage());
		            }
		        } else {
		            log.warn("Null found in the request data.");
		        }
		    }
		
	}
	
	private void insertTuition(CepPersonRequest req) {
		log.debug("Insert Tuition");
		try {
			for (CepTuitionRequest data : req.getTuitionList()) {
				CepTuition entity = new CepTuition();
				data.setPerUUID(req.getPerUUID());
				this.cepTuitionDao.create(this.cepTuitionDto.reqToEntity(entity, data));
			}
		} catch (Exception e) {

		}
	}
	private void insertSpouse(CepPersonRequest req) {
		log.debug("Insert Spouse");
		try {
			for (CepSpouseRequest data : req.getSpouseList()) {
				CepSpouse entity = new CepSpouse();
				data.setPerUUID(req.getPerUUID());
				this.cepSpouseDao.create(this.cepSpouseDto.reqToEntity(entity, data));
			}
		} catch (Exception e) {

		}
	}
	private void insertKid(CepPersonRequest req) {
		log.debug("Insert Kid");
		try {
			for (CepKidRequest data : req.getKidList()) {
				CepKid entity = new CepKid();
				data.setPerUUID(req.getPerUUID());
				this.cepKidDao.create(this.cepKidDto.reqToEntity(entity, data));
			}
		} catch (Exception e) {

		}
	}

	@Override
	public CepPersonResponse insert(CepPersonRequest req) {
		// TODO Auto-generated method stub
		log.debug("Begin Insert Manager...");
		CepPersonResponse response = null;
		CepPerson entity = new CepPerson();

		try {
			this.db.begin();
			insertSpouse(req);
			insertTuition(req);
			insertKid(req);
			entity = this.cepPersonDto.reqToEntity(entity, req);
			this.cepPersonDao.create(entity);
			this.db.commit();
			response = CepPersonResponse.builder().status(SimpleStatus.SUCCESS).build();
			return response;
		} catch (Exception e) {
			this.db.rollback();
			log.error("Exception : {}", e);
			response.setError(SimpleStatus.ERROR);
			return response;
		} finally {
			log.debug("End insert....");
			response = null;
		}

	}

	@Override
	public CepPersonResponse update(CepPersonRequest req) {
		log.debug("Begin update ");
		CepPersonResponse res = null;
		try {
			this.db.begin();

			CepPerson newEntity = this.cepPersonDao.find(req.getPerUUID());
			log.debug("test {}", newEntity);
			CepPerson oldEntity = new CepPerson();
			TransformUtils.copyProperties(oldEntity, newEntity);

			newEntity = this.cepPersonDto.reqToEntity(newEntity, req);
			
			cepPersonDao.update(newEntity);
			deleteSpo(req);
			deleteTui(req);
			deleteKid(req);
			this.db.flush();
			insertSpouse(req);
			insertTuition(req);
			insertKid(req);
			
			this.db.commit();
			res = CepPersonResponse.builder().status(SimpleStatus.SUCCESS).build();
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
	public CepPersonResponse delete(CepPersonRequest req) {
		log.debug("Begin delete....");
		CepPersonResponse res = null;
		CepPerson entity= new CepPerson();
 
    	
    	try {
    		this.db.begin();
    		entity = this.cepPersonDao.find(req.getPerUUID());
    		if (entity != null) {
				this.cepPersonDao.delete(entity);
				deleteSpo(req);
				deleteTui(req);
				deleteKid(req);
				
			}
    		this.db.commit();
    		res = CepPersonResponse.builder().status(SimpleStatus.SUCCESS).build();
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
	
	
	@Override
	public CepPersonResponse search(CepPersonRequest req) {
		log.debug("Begin Manager search... ");

		CepPersonResponse res = null;
		CepPersonRequest emReq = null;
		  List<Object[]> oblList = new ArrayList<>();

		  try {
		   oblList = this.cepPersonDao.search(req);
		   res = CepPersonResponse.builder().dataRequestList(new ArrayList<>()).build();
		   if (!oblList.isEmpty()) {
		    for (Object[] obj : oblList) {
		     emReq = CepPersonRequest.builder().cepTuitionReq(CepTuitionRequest.builder().build()).build();
		     emReq.setPerUUID((String) obj[0]);
		     emReq.getCepTuitionReq().setReqID((String) obj[1]);
		     if (!StringUtils.isNullOrEmpty((String) obj[2])) {
		      emReq.getCepTuitionReq().setReqDate(DateUtils.convertStringDateToDate((String) obj[2]));
		     }
		     emReq.getCepTuitionReq().setEduYear((String) obj[3]);
		     emReq.getCepTuitionReq().setSemester((String) obj[4]);
		     emReq.setPerTitleName((String) obj[5]);
		     emReq.setPerFirstName((String) obj[6]);
		     emReq.setPerLastName((String) obj[7]);
		     emReq.getCepTuitionReq().setWelfareAmount((String) obj[8]);
		     emReq.getCepTuitionReq().setReqStatus((String) obj[9]);

		     res.getDataRequestList().add(emReq);
		    }

		   }
		   res.setStatus(SimpleStatus.SUCCESS);
		   return res;
		  } catch (Exception e) {
		   log.error("Exception Manager search {}", e);
		   res = CepPersonResponse.builder().dataRequestList(new ArrayList<>()).status(SimpleStatus.FAIL).build();
		   return res;
		  } finally {
		   res = null;
		   log.debug("End Manager search... ");
		  }
	}
}
