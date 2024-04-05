package com.summitthai.cr.apprentice.kids.manager;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.kids.dao.KidinfoDao;
import com.summitthai.cr.apprentice.kids.dto.KidInfoDto;
import com.summitthai.cr.apprentice.kids.entity.Kidinfo;
import com.summitthai.cr.apprentice.kids.model.DataKidRequest;
import com.summitthai.cr.apprentice.kids.model.KidInfoResponse;
import com.summitthai.cr.apprentice.status.SimpleStatus;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@Data
public class KidManager implements KidManageable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
    private ApprenticeJpaService db;
	
	@Inject
	private KidinfoDao kidinfoDao;
	
	
	private KidInfoDto kidInfoDto;
	
	
	public KidManager() {
		this.kidInfoDto = new KidInfoDto();

	}
	
	public KidManager(ApprenticeJpaService db, KidinfoDao kidinfoDao) {
		  this.setDb(db);
		 }
	@Override
	public KidInfoResponse findByReq(DataKidRequest req) {
		log.debug("Begin Manager findByReq... ");
		DataKidRequest request = null;
		KidInfoResponse res= null;
		List<Kidinfo> entityList = new ArrayList<>();  
				
		try {
			entityList = this.kidinfoDao.findByReq(req);
			res = KidInfoResponse.builder().dataRequestList(new ArrayList<>()).build();
			if (!entityList.isEmpty()) {
				for (Kidinfo data : entityList) {
						request = this.kidInfoDto.entityToReq(data);
						request.setKidFullName(request.getKidFname().concat(" ").concat(request.getKidLname()));
					res.getDataRequestList().add(request);
				}
			}
			
			res.setStatus(SimpleStatus.SUCCESS);
			return res;
			
		} catch (Exception e) {
			log.error("Exception Manager findByReq {}",e);
			res = KidInfoResponse.builder().dataRequestList(new ArrayList<>()).status(SimpleStatus.FAIL).build();
			return res;
		} finally {
			res = null;
			request = null;
			entityList = null;
			log.debug("End Manager findByReq... ");
		}
		
	}

//	@Override
//	public KidInfoResponse insert(DataKidRequest req) {
//		log.debug("Begin Manager insert");
//		KidInfoResponse res = null;
//		Kidinfo entity = new Kidinfo();
//		
//		try {
//			this.db.begin();
//			
//			entity  =  this.kidInfoDto.reqToEntity(entity,req);
//			this.kidinfoDao.create(entity);
//			this.db.commit();
//			
//			res = KidInfoResponse.builder().status(SimpleStatus.SUCCESS).build();
//			return res;
//			
//		} catch (Exception e) {
//			this.db.rollback();
//			log.error("Exception Manager insert",e);
//			
//			res = KidInfoResponse.builder().status(SimpleStatus.FAIL).build();
//			
//			return res;
//			
//		} finally {
//			
//			res = null;
//			entity = null;
//			log.debug("End Manager insert");
//		} 
//	}
//	
//	
//	
//
//	@Override
//	public KidInfoResponse update(DataKidRequest req) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public KidInfoResponse delete(DataKidRequest req) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
