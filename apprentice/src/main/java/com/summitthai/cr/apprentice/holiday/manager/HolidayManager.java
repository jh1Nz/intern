package com.summitthai.cr.apprentice.holiday.manager;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.summitthai.cr.apprentice.holiday.dao.HolidayDao;
import com.summitthai.cr.apprentice.holiday.dto.HolidayDto;
import com.summitthai.cr.apprentice.holiday.entity.Holiday;
import com.summitthai.cr.apprentice.holiday.model.HolidayRequest;
import com.summitthai.cr.apprentice.holiday.model.HolidayResponse;
import com.summitthai.cr.apprentice.jpa.ApprenticeJpaService;
import com.summitthai.cr.apprentice.persanal.model.PersanalResponse;
import com.summitthai.cr.apprentice.status.SimpleStatus;

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

	private HolidayDto holidayDto;

	public HolidayManager() {
		this.holidayDto = new HolidayDto();
	}

	@Override
	public HolidayResponse findByReq(HolidayRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HolidayResponse insert(HolidayRequest req) {
		log.debug("Begin Insert Manager...");
		HolidayResponse res = null;
		Holiday entity = new Holiday();

		try {

			this.db.begin();
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
		// TODO Auto-generated method stub
		return null;
	}

}
