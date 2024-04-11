package com.summitthai.cr.apprentice.deptH.dto;

import java.io.Serializable;

import com.summitthai.cr.apprentice.deptH.entity.HolidayDept;
import com.summitthai.cr.apprentice.deptH.model.HolidayDeptRequest;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@ToString
public class HolidayDeptDto implements Serializable {
	private static final long serialVersionUID = 1L;

	public HolidayDeptRequest entityToReq(HolidayDept entity) {
		HolidayDeptRequest req = null;
		req = HolidayDeptRequest.builder().build();
		try {
			req.setHolidayDeptID(entity.getHolidayDeptID());
			req.setHolidayID(entity.getHolidayID());
			req.setHolidayDept(entity.getHolidayDept());

			return req;
		} catch (Exception e) {
			log.error("Show Error {}", e);
			return null;
		} finally {
			req = null;
		}
	}

	public HolidayDept reqToEntity(HolidayDept entity, HolidayDeptRequest req) {

		entity.setHolidayDeptID(req.getHolidayDeptID());
		entity.setHolidayID(req.getHolidayID());
		entity.setHolidayDept(req.getHolidayDept());
		return entity;
	}
}
