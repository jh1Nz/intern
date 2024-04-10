package com.summitthai.cr.apprentice.view;

import java.io.Serializable;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.summitthai.cr.apprentice.exception.DefaultException;
import com.summitthai.cr.apprentice.holiday.manager.HolidayManager;
import com.summitthai.cr.apprentice.holiday.model.HolidayRequest;
import com.summitthai.cr.apprentice.holiday.model.HolidayResponse;
import com.summitthai.cr.apprentice.massage.MassageComplete;
import com.summitthai.cr.apprentice.massage.MassageError;
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

	private HolidayRequest formEdit;

	@Inject
	private HolidayManager holidayManager;

	public Tmp01005Bean() {
		this.formEdit = HolidayRequest.builder().build();
	}

	@PostConstruct
	public void init() {
		log.debug("Begin init");
		this.mode = MODE_SEARCH;
		log.debug("End init");
	}

	// For Change Mode สำหรับเปลี่ยนโหมดบนหน้าจอ
	public void onPageInsert() {
		log.debug("Begin OnPageInsert");
		this.mode = MODE_INSERT;
		log.debug("End OnPageInsert");
	}

	public void onPageSearch() {
		log.debug("Begin OnPageSearch ");
		this.mode = MODE_SEARCH;
		log.debug("End OnPageSearch");
	}
	


	public void prepareData() {
		log.debug("Begin PrepareData...");
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
			res = this.holidayManager.insert(formEdit);
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
	
	private void prepareData2() {
	log.debug("Begin prepareData..");
	try {
		if (this.mode.equals(MODE_INSERT)) {
			this.formEdit.setHolidayID(UUID.randomUUID().toString());
		}
	} catch (Exception e) {
		log.error("Exception prepareData",e);
	}finally {
		log.debug("End prepareData..");
	}
	
	
}

public void insertButtonOnClick2() {
	log.debug("Begin insertButtonOnClick");
	HolidayResponse res = null;
	try {
		this.prepareData2();
		res = this.holidayManager.insert(this.formEdit);
        if (res.getStatus().equals(SimpleStatus.SUCCESS)) {
        	XspUtils.scrollToTop();
            this.onPageSearch();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", MassageComplete.INSERT_COMPLETE));
            
         } else {
        	 XspUtils.scrollToTop();
        	 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MassageError.INSERT_FAIL));
             throw new DefaultException(MassageError.INSERT_FAIL);
         }
        
	} catch (DefaultException de) {
		log.error("DefaultException insertButtonOnClick {}",de);
	} catch (Exception e) {
		log.error("Exception insertButtonOnClick {}",e);
	} finally {
		log.debug("End insertButtonOnClick");
	}
}


}
