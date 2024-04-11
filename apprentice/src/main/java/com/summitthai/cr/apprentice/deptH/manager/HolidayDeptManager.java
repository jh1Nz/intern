package com.summitthai.cr.apprentice.deptH.manager;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.summitthai.cr.apprentice.deptH.dao.HolidayDeptDao;
import com.summitthai.cr.apprentice.deptH.dto.HolidayDeptDto;
import com.summitthai.cr.apprentice.deptH.entity.HolidayDept;
import com.summitthai.cr.apprentice.deptH.model.HolidayDeptRequest;
import com.summitthai.cr.apprentice.deptH.model.HolidayDeptResponse;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.status.SimpleStatus;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
@Data
public class HolidayDeptManager implements HolidayDeptManageable {
	private static final long serialVersionUID = 1L;
	@Inject
	private ApprenticeJpaService db;
	@Inject
	private HolidayDeptDao holidayDeptDao;

	private HolidayDeptDto holidayDeptDto;

	public HolidayDeptManager() {
		this.holidayDeptDto = new HolidayDeptDto();
	}

	public HolidayDeptManager(ApprenticeJpaService db, HolidayDeptDao holidayDeptDao) {
		this.setDb(db);
		
	}

	@Override
	public HolidayDeptResponse findByReq(HolidayDeptRequest req) {
		// TODO Auto-generated method stub
		log.debug("Begin findByReq");
		HolidayDeptRequest request = null;
		HolidayDeptResponse res = null;
		List<HolidayDept> entityList = new ArrayList<>();

		try {
			entityList = this.holidayDeptDao.findByReq(req);
			res = HolidayDeptResponse.builder().dataRequestList(new ArrayList<>()).build();
			
			for(HolidayDept data : entityList) {
				request = this.holidayDeptDto.entityToReq(data);
				res.getDataRequestList().add(request);
			}
			res = HolidayDeptResponse.builder().status(SimpleStatus.SUCCESS).build();
			return res;

		} catch (Exception e) {
			log.error("Exception findByReq : {}", e);
			res = HolidayDeptResponse.builder().status(SimpleStatus.FAIL).build();
			return res;
		} finally {
			log.debug("End XspPersonResponse findByReq...");
		}

	}

}
