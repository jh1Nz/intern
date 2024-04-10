package com.summitthai.cr.apprentice.holiday.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.holiday.entity.Holiday;
import com.summitthai.cr.apprentice.holiday.model.HolidayRequest;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString
public class HolidayDto implements Serializable{
	private static final long serialVersionUID = 1L;

	public HolidayRequest entityToReq(Holiday entity) {
		HolidayRequest req = null;
		req = HolidayRequest.builder().build();
		try {
			req.setHolidayID(entity.getHolidayID());
			req.setDate(entity.getDate());
			req.setMonth(entity.getMonth());
			req.setYear(entity.getYear());
			req.setHolidayName(entity.getHolidayName());
			req.setEffectiveType(entity.getEffectiveType());
			req.setPosGroup(entity.getPosGroup());
			req.setActiveFlag(entity.getActiveFlag());
			req.setEffectiveDepartment(entity.getEffectiveDepartment());
			req.setDepartment(entity.getDepartment());

			return req;
		} catch (Exception e) {
			log.error("Show Error {}", e);
			return null;
		} finally {
			req = null;
		}

	}

	public Holiday reqToEntity(Holiday entity, HolidayRequest req) {

		entity.setHolidayID(req.getHolidayID());
		entity.setDate(req.getDate());
		entity.setMonth(req.getMonth());
		entity.setYear(req.getYear());
		entity.setHolidayName(req.getHolidayName());
		entity.setEffectiveType(req.getEffectiveType());
		entity.setPosGroup(req.getPosGroup());
		entity.setActiveFlag(req.getActiveFlag());
		entity.setEffectiveDepartment(req.getEffectiveDepartment());
		entity.setDepartment(req.getDepartment());
		return entity;
	}
}
