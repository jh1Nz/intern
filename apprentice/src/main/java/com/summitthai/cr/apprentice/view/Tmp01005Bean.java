package com.summitthai.cr.apprentice.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.summitthai.cr.apprentice.deptH.model.HolidayDeptRequest;
import com.summitthai.cr.apprentice.exception.DefaultException;
import com.summitthai.cr.apprentice.holiday.manager.HolidayManager;
import com.summitthai.cr.apprentice.holiday.model.HolidayRequest;
import com.summitthai.cr.apprentice.holiday.model.HolidayResponse;
import com.summitthai.cr.apprentice.massage.MassageComplete;
import com.summitthai.cr.apprentice.massage.MassageError;
import com.summitthai.cr.apprentice.persanal.model.PersanalResponse;
import com.summitthai.cr.apprentice.status.SimpleStatus;
import com.summitthai.cr.apprentice.utils.XspUtils;
import com.summitthai.sdd.faces.view.base.ViewBase;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Named
@ViewScoped
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class Tmp01005Bean extends ViewBase implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> dayList;
	private List<HolidayRequest> holidayList;
	private List<HolidayDeptRequest> deptList;

	private HolidayRequest formEdit;
	private HolidayDeptRequest formEditTable;
	private HolidayRequest formCriteria;

	@Inject
	private HolidayManager holidayManager;

	public Tmp01005Bean() {
		this.formEdit = HolidayRequest.builder().build();
		this.formEditTable = HolidayDeptRequest.builder().build();
		this.formCriteria = HolidayRequest.builder().build();
	}

	@PostConstruct
	public void init() {
		log.debug("Begin init");
		this.onPageSearch();
		HolidayResponse res = this.holidayManager.findByReq(HolidayRequest.builder().posGroup("ข้าราชการ").build());
		log.debug("{}",res);
		log.debug("End init");
	}

	// For Change Mode สำหรับเปลี่ยนโหมดบนหน้าจอ
	public void onPageInsert() {
		log.debug("Begin OnPageInsert");
		this.mode = MODE_INSERT;
		this.prepareInsert();
		log.debug("End OnPageInsert");
	}

	public void prepareInsert() {
		log.debug("Begin prepareInsert");
		this.formEdit = HolidayRequest.builder().date("1").month("มกราคม").effectiveType("มีผลทั้งหมด")
				.posGroup("ข้าราชการ").activeFlag("true").build();
		this.setdayLits();
		this.deptList = new ArrayList<>();
		this.formEdit.setDeptList(new ArrayList<>());
		log.debug("End prepareInsert");
	}

	public void onPageSearch() {
		log.debug("Begin OnPageSearch ");
		this.mode = MODE_SEARCH;
		this.holidayList = new ArrayList<>();
		log.debug("End OnPageSearch");
	}

	public List<SelectItem> getDayItems() {
		List<SelectItem> items = new ArrayList<>();
		for (String day : dayList) {
			items.add(new SelectItem(day, day));
		}
		return items;
	}

	private void setdayLits() {

		this.dayList = new ArrayList<String>();
		for (int i = 1; i <= 31; i++) {

			this.dayList.add(String.valueOf(i));

		}

	}

	public void setTable() {
		log.debug("Begin setTable");

		 this.deptList.add(HolidayDeptRequest.builder().holidayDeptID(UUID.randomUUID().toString()).build());

		log.debug("End setTable");
	}


	private void prepareData() {
		log.debug("Begin prepareData..");
		try {
			if (this.mode.equals(MODE_INSERT)) {
				this.formEdit.setHolidayID(UUID.randomUUID().toString());
			}
		} catch (Exception e) {
			log.error("Exception prepareData", e);
		} finally {
			log.debug("End prepareData..");
		}

	}

	public void insertButtonOnClick() {
		log.debug("Begin insertButtonOnClick");
		HolidayResponse res = null;
		try {

			this.prepareData();
			if (!this.deptList.isEmpty()) {
				this.formEdit.getDeptList().addAll(this.deptList);
			}
			res = this.holidayManager.insert(this.formEdit);
			if (res.getStatus().equals(SimpleStatus.SUCCESS)) {
				XspUtils.scrollToTop();
				this.onPageSearch();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "", MassageComplete.INSERT_COMPLETE));

			} else {
				XspUtils.scrollToTop();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MassageError.INSERT_FAIL));
				throw new DefaultException(MassageError.INSERT_FAIL);
			}

		} catch (DefaultException de) {
			log.error("DefaultException insertButtonOnClick {}", de);
		} catch (Exception e) {
			log.error("Exception insertButtonOnClick {}", e);
		} finally {
			log.debug("End insertButtonOnClick");
		}
	}
	public void searchButtonOnclick() {
		log.debug("Begin searchButtonOnclick");
		HolidayResponse res = null;
		this.holidayList = new ArrayList<>();
		try {
			res = this.holidayManager.findByReq(formCriteria);
			
			if(!res.getDataRequestList().isEmpty()) {
				
				this.holidayList.addAll(res.getDataRequestList());
			}
			
			
		} catch (Exception e) {
			log.debug("Exception searchButtonOnclick {}",e);
			
		}
		
	}

}
