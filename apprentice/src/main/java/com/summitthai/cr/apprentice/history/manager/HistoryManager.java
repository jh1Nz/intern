package com.summitthai.cr.apprentice.history.manager;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.summitthai.cr.apprentice.history.dao.HistoryDao;
import com.summitthai.cr.apprentice.history.dto.HistoryDto;
import com.summitthai.cr.apprentice.history.entity.History;
import com.summitthai.cr.apprentice.history.model.DataKidEducationRequest;
import com.summitthai.cr.apprentice.history.model.HistoryResponse;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.kids.entity.Kidinfo;
import com.summitthai.cr.apprentice.kids.model.DataKidRequest;
import com.summitthai.cr.apprentice.kids.model.KidInfoResponse;
import com.summitthai.cr.apprentice.status.SimpleStatus;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@ApplicationScoped


@Data
public class HistoryManager implements HistoryManageable {
	
	
	@Inject
    private ApprenticeJpaService db;
	
	@Inject
	private HistoryDao historyDao;
	
	
	private HistoryDto historyDto;
	
	
	public HistoryManager() {
		this.historyDto = new HistoryDto();

	}

	@Override
	public HistoryResponse findByReq(DataKidEducationRequest req) {
		log.debug("Begin Manager findByReq... ");
		DataKidEducationRequest request = null;
		HistoryResponse res= null;
		List<History> entityList = new ArrayList<>();  
				
		try {
			entityList = this.historyDao.findByReq(req);
			res = HistoryResponse.builder().dataRequestList(new ArrayList<>()).build();
			if (!entityList.isEmpty()) {
				for (History data : entityList) {
						request = this.historyDto.entityToReq(data);
						request.setKidFullname((request.getKidIname().concat(" ").concat(request.getKidFname().concat(" ").concat(request.getKidLname()))));
					
					res.getDataRequestList().add(request);
				}
			}
			
			res.setStatus(SimpleStatus.SUCCESS);
			return res;
			
		} catch (Exception e) {
			log.error("Exception Manager findByReq {}",e);
			res = HistoryResponse.builder().dataRequestList(new ArrayList<>()).status(SimpleStatus.FAIL).build();
			return res;
		} finally {
			res = null;
			request = null;
			entityList = null;
			log.debug("End Manager findByReq... ");
		}
	}

	

}
