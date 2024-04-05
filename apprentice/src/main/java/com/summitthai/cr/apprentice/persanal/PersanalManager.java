package com.summitthai.cr.apprentice.persanal;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.summitthai.cr.apprentice.history.dao.HistoryDao;
import com.summitthai.cr.apprentice.history.dto.HistoryDto;
import com.summitthai.cr.apprentice.history.entity.History;
import com.summitthai.cr.apprentice.history.model.DataKidEducationRequest;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.kids.dao.KidinfoDao;
import com.summitthai.cr.apprentice.kids.dto.KidInfoDto;
import com.summitthai.cr.apprentice.kids.entity.Kidinfo;
import com.summitthai.cr.apprentice.kids.model.DataKidRequest;
import com.summitthai.cr.apprentice.persanal.dao.PersanalDao;
import com.summitthai.cr.apprentice.persanal.dto.PersanalDto;
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
public class PersanalManager implements PersanalManageable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private ApprenticeJpaService db;
	
	@Inject
	private PersanalDao persanalDao;
	
	@Inject
	private KidinfoDao kidinfoDao;
	
	@Inject
	private HistoryDao historyDao;
	
	private KidInfoDto kidInfoDto;
	private HistoryDto historyDto;
	private PersanalDto persanalDto;
	
	public PersanalManager() {
		this.persanalDto = new PersanalDto();
		this.kidInfoDto = new KidInfoDto();
		this.historyDto = new HistoryDto();
		
	}
	
	
	
	@Override
	public PersanalResponse findByReq(PersanalRequest req) {
		log.debug("Begin Manager findByReq... ");
		
		PersanalResponse res = null;
		List<PersanalInfo> entityList = new ArrayList<>();
		
		try {
			entityList = this.persanalDao.findByReq(req);
			res = PersanalResponse.builder().dataRequestList(new ArrayList<>()).build();
			if (!entityList.isEmpty()) {
				for (PersanalInfo data : entityList) {
//					log.debug("1 findByReq {}",entityList);
					PersanalRequest request = PersanalRequest.builder().build();
					request = this.persanalDto.entityToReq(data);
					res.getDataRequestList().add(request);
//					log.debug("2 findByReq {}",request.getPerId());
//					log.debug("3 findByReq {}",res.getDataRequestList());
				}
				log.debug("2 findByReq {}",res.getDataRequestList().get(0).getPerId());
			}
			res.setStatus(SimpleStatus.SUCCESS);
			return res;
			
		} catch (Exception e) {
			log.error("Exception Manager findByReq {}",e);
			res = PersanalResponse.builder().dataRequestList(new ArrayList<>()).status(SimpleStatus.FAIL).build();
			return res;
		} finally {
			res = null;
			entityList = null;
			log.debug("End Manager findByReq... ");
		}
				


		
	}

	@Override
	public PersanalResponse insert(PersanalRequest req) {
			log.debug("Begin Manager insert");
			PersanalResponse res = null;
			PersanalInfo entity = new PersanalInfo();
			try {
				this.db.begin();
				insertDataKids(req);
				insertDataEducation(req);
				entity  =  this.persanalDto.reqToEntity(entity,req);
				this.persanalDao.create(entity);
				this.db.commit();
				
				res = PersanalResponse.builder().status(SimpleStatus.SUCCESS).build();
				return res;
				
			} catch (Exception e) {
				this.db.rollback();
				log.error("Exception Manager insert",e);
				
				res = PersanalResponse.builder().status(SimpleStatus.FAIL).build();
				
				return res;
				
			} finally {
				
				res = null;
				entity = null;
				log.debug("End Manager insert");
			} 
			

		
	}
	
		private void insertDataKids(PersanalRequest req) {
			log.debug("Begin insertDataKids");
			try {
				for(DataKidRequest data : req.getKidslist() ) {
					Kidinfo entity = new Kidinfo();
					data.setPersanalInfoId(req.getPersanalInfoId());
					this.kidinfoDao.create(this.kidInfoDto.reqToEntity(entity, data));
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		private void insertDataEducation(PersanalRequest req) {
			log.debug("Begin insertDataKids");
			try {
				for(DataKidEducationRequest data : req.getEducationList() ) {
					History entity = new History();
					data.setPersanalInfoId(req.getPersanalInfoId());
					this.historyDao.create(this.historyDto.reqToEntity(entity, data));
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}

	@Override
	public PersanalResponse update(PersanalRequest req) {
		log.debug("Begin update ");
		PersanalResponse res= null;
		
		try {
			this.db.begin();
			
			PersanalInfo newEntity = this.persanalDao.find(req.getPersanalInfoId());
			log.debug("test {}",newEntity);
			PersanalInfo oldEntity = new PersanalInfo();
			TransformUtils.copyProperties(oldEntity, newEntity);
			
			newEntity = this.persanalDto.reqToEntity(newEntity, req);
			persanalDao.update(newEntity);
			
			this.db.commit();
    		res = PersanalResponse
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
	public PersanalResponse delete(PersanalRequest req) {
		
    	log.debug("Begin delete....");
    	PersanalResponse res = null;
    	PersanalInfo entity = new PersanalInfo();
    	
    	try {
    		this.db.begin();
    		entity = this.persanalDao.find(req.getPersanalInfoId());
    		if (entity != null) {
				this.persanalDao.delete(entity);
				deleteKid(req);
				deleteEdukid(req);
				
				
			}
    		this.db.commit();
    		res = PersanalResponse.builder().status(SimpleStatus.SUCCESS).build();
    		return res;
    	}catch (Exception e) {
			log.error("Exception : {}", e);
			res.setError("ERROR");
            return res;
		}finally {
			log.debug("End delete....");
			res     = null;
		}
    	

		
	}
	
	
	private void deleteKid(PersanalRequest req) {
	    List<DataKidRequest> kidList = req.getKidslist();
	    if (kidList == null) {
	        log.warn("No kid data found in the request.");
	        return; // No need to proceed if there's no kid data
	    }

	    for (DataKidRequest data : kidList) {
	        String kidID = data.getKidInfoId(); // Assuming kidID is of type Long, adjust accordingly
	        if (kidID != null) {
	            try {
	                Kidinfo entityKid = this.kidinfoDao.find(kidID);
	                if (entityKid != null) {
	                    this.kidinfoDao.delete(entityKid);
	                }
	            } catch (Exception e) {
	                log.error("Exception while deleting kid with ID {}: {}", kidID, e.getMessage());
	            }
	        } else {
	            log.warn("Null kidID found in the request data.");
	        }
	    }
	}
	
	
	private void deleteEdukid(PersanalRequest req) {
	    List<DataKidEducationRequest> eduLish = req.getEducationList();
	    if (eduLish == null) {
	        log.warn("No kid data found in the request.");
	        return; // No need to proceed if there's no kid data
	    }

	    for (DataKidEducationRequest data : eduLish) {
	        String eduID = data.getEducationHistoryId(); // Assuming kidID is of type Long, adjust accordingly
	        if (eduID != null) {
	            try {
	                History entityEdu = this.historyDao.find(eduID);
	                if (entityEdu != null) {
	                    this.historyDao.delete(entityEdu);
	                }
	            } catch (Exception e) {
	                log.error("Exception while deleting Education with ID {}: {}", eduID, e.getMessage());
	            }
	        } else {
	            log.warn("Null EducationId found in the request data.");
	        }
	    }
	}

}
