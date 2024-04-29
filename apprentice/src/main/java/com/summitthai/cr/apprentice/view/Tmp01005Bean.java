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

import org.primefaces.event.SelectEvent;

import com.summitthai.cr.apprentice.deptH.manager.HolidayDeptManager;
import com.summitthai.cr.apprentice.deptH.model.HolidayDeptRequest;
import com.summitthai.cr.apprentice.deptH.model.HolidayDeptResponse;
import com.summitthai.cr.apprentice.exception.DefaultException;
import com.summitthai.cr.apprentice.history.model.DataKidEducationRequest;
import com.summitthai.cr.apprentice.history.model.HistoryResponse;
import com.summitthai.cr.apprentice.holiday.manager.HolidayManager;
import com.summitthai.cr.apprentice.holiday.model.HolidayRequest;
import com.summitthai.cr.apprentice.holiday.model.HolidayResponse;
import com.summitthai.cr.apprentice.kids.model.DataKidRequest;
import com.summitthai.cr.apprentice.kids.model.KidInfoResponse;
import com.summitthai.cr.apprentice.massage.MassageComplete;
import com.summitthai.cr.apprentice.massage.MassageError;
import com.summitthai.cr.apprentice.persanal.model.PersanalRequest;
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
	private HolidayRequest formSelected;

	@Inject
	private HolidayManager holidayManager;

	@Inject
	private HolidayDeptManager holidayDeptManager;

	public Tmp01005Bean() {
		this.formEdit = HolidayRequest.builder().build();
		this.formEditTable = HolidayDeptRequest.builder().build();
		this.formCriteria = HolidayRequest.builder().build();
		this.formSelected = HolidayRequest.builder().build();
	}

	@PostConstruct
	public void init() {
		log.debug("Begin init");
		this.onPageSearch();
		HolidayResponse res = this.holidayManager.findByReq(HolidayRequest.builder().posGroup("ข้าราชการ").build());
		log.debug("{}", res);
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
		this.formEdit = HolidayRequest.builder().date("1").month("มกราคม").effectiveType("true").posGroup("ข้าราชการ")
				.activeFlag("ใช้งาน").resultDept("false").build();
		this.deptList = new ArrayList<>();
		this.formEdit.setDeptList(new ArrayList<>());
		log.debug("End prepareInsert");
	}

	public void onPageSearch() {
		log.debug("Begin OnPageSearch ");
		this.mode = MODE_SEARCH;
		this.formCriteria = HolidayRequest.builder().build();
		this.holidayList = new ArrayList<>();
		log.debug("End OnPageSearch");
	}

	public List<SelectItem> getDaysOptions() {
		List<SelectItem> options = new ArrayList<>();
		for (int i = 1; i <= 31; i++) {
			options.add(new SelectItem(i, String.valueOf(i)));
		}
		return options;
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
	public void resetTable() {
		this.deptList = new ArrayList<>();
	}

	public void removeDataList(int index) {
		log.debug("Begin removeDataList...");
		this.deptList.remove(index);
		log.debug("End removeDataList...");
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

			if (!res.getDataRequestList().isEmpty()) {

				this.holidayList.addAll(res.getDataRequestList());
			}

		} catch (Exception e) {
			log.debug("Exception searchButtonOnclick {}", e);

		}

	}

	public void onRowSelect(SelectEvent event) {
		log.debug("Begin onRowSelect...");
		try {
			this.mode = MODE_UPDATE;
			this.formEdit = HolidayRequest.builder().build();
			this.formEdit = this.formSelected;
			
			
			HolidayDeptRequest req = HolidayDeptRequest.builder().holidayID(this.formEdit.getHolidayID()).build();
			HolidayDeptResponse res = this.holidayDeptManager.findByReq(req);
			this.formEdit.setDeptList(res.getDataRequestList());
			if (res != null && !res.getDataRequestList().isEmpty()) {
				this.deptList = res.getDataRequestList();
			} else {
				this.deptList = new ArrayList<>();
			}

		} catch (Exception e) {
			log.error("Error : {}", e);
		} finally {
			log.debug("End onRowSelect...");
		}

	}

	public void updateButtonOnClick() {
		log.debug("Begin updateButtonOnClick ");
		HolidayResponse holRes = null;

		try {
			this.prepareData();
			if (!this.deptList.isEmpty()) {
				this.formEdit.setDeptList(new ArrayList<>());
				this.formEdit.getDeptList().addAll(this.deptList);
			}
			holRes = this.holidayManager.update(this.formEdit);
			if (holRes.getStatus().equals("SUCCESS")) {

				this.onPageSearch();
				XspUtils.scrollToTop();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "", MassageComplete.UPDATE_COMPLETE));
			} else {
				XspUtils.scrollToTop();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MassageError.UPDATE_FAIL));
				throw new DefaultException(MassageError.UPDATE_FAIL);

			}

		} catch (DefaultException ex) {
			log.error("Exception :{}", ex);
			messageError(ex);
		} catch (Exception e) {
			messageError(e);
		} finally {
			XspUtils.scrollToTop();

			log.debug("End...updateButtonOnClick...");
			holRes = null;
		}

	}

	public void deleteButtonOnClick() {
		log.debug("Begin deletButtonOnClick...");
		
		HolidayResponse resHol = null;

		try {
			resHol = this.holidayManager.delete(this.formEdit);

			if (resHol.getStatus().equals("SUCCESS")) {
				this.onPageSearch();
				XspUtils.scrollToTop();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "", MassageComplete.DELETE_COMPLETE));
			} else {
				XspUtils.scrollToTop();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MassageError.DELETE_FAIL));
				throw new DefaultException(MassageError.DELETE_FAIL);
			}
		} catch (Exception e) {
			log.error("Exception :{}", e);
			messageError(e);
		} finally {
			log.debug("End...deletButtonOnClick...");
			resHol = null;
		}

	}

}
