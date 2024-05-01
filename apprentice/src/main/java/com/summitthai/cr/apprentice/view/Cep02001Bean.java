package com.summitthai.cr.apprentice.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.summitthai.cr.apprentice.cep.kid.manager.CepKidManager;
import com.summitthai.cr.apprentice.cep.kid.model.CepKidRequest;
import com.summitthai.cr.apprentice.cep.kid.model.CepKidResponse;
import com.summitthai.cr.apprentice.cep.person.manager.CepPersonManager;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonRequest;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonResponse;
import com.summitthai.cr.apprentice.cep.spouse.manager.CepSpouseManager;
import com.summitthai.cr.apprentice.cep.spouse.model.CepSpouseRequest;
import com.summitthai.cr.apprentice.cep.spouse.model.CepSpouseResponse;
import com.summitthai.cr.apprentice.cep.tuition.manager.CepTuitionManager;
import com.summitthai.cr.apprentice.cep.tuition.model.CepTuitionRequest;
import com.summitthai.cr.apprentice.cep.tuition.model.CepTuitionResponse;
import com.summitthai.cr.apprentice.exception.DefaultException;
import com.summitthai.cr.apprentice.holiday.model.HolidayResponse;
import com.summitthai.cr.apprentice.massage.MassageComplete;
import com.summitthai.cr.apprentice.massage.MassageError;
import com.summitthai.cr.apprentice.status.SimpleStatus;
import com.summitthai.cr.apprentice.utils.XspUtils;
import com.summitthai.sdd.faces.view.base.ViewBase;
import com.summitthai.sdd.sys.util.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Named
@ViewScoped
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class Cep02001Bean extends ViewBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private CepPersonRequest formEdit;
	private CepPersonRequest formCriteria;
	private CepPersonRequest formSelected;
	private CepSpouseRequest formEditSpouse;
	private CepTuitionRequest formEditTuition;

	@Inject
	private CepPersonManager cepPersonManager;
	@Inject
	private CepSpouseManager cepSpouseManager;
	@Inject
	private CepTuitionManager cepTuitionManager;
	@Inject
	private CepKidManager cepKidManager;

	private List<CepTuitionRequest> tuiList;
	private List<CepSpouseRequest> spoList;
	private List<CepKidRequest> kidList;

	private List<CepPersonRequest> qList;
	
	

	public Cep02001Bean() {
		this.formCriteria = CepPersonRequest.builder().build();
		this.formEdit = CepPersonRequest.builder().build();
		this.formSelected = CepPersonRequest.builder().build();
		this.formEditSpouse = CepSpouseRequest.builder().build();
		this.formEditTuition = CepTuitionRequest.builder().build();

	}

	@PostConstruct
	public void init() {
		log.debug("Begin init");
		this.onPageSearch();
		log.debug("End init");
	}

	public void onPageSearch() {
		log.debug("Begin OnPageSearch ");
		this.mode = MODE_SEARCH;
		this.qList = new ArrayList<>();
		this.formCriteria.setCepTuitionReq(CepTuitionRequest.builder().build());
		log.debug("End OnPageSearch");
	}

	public void onPageInsert() {
		log.debug("Begin OnPageInsert");
		this.mode = MODE_INSERT;
		this.preparePageInsert();
		log.debug("End OnPageInsert");
	}

	public void preparePageInsert() {
		log.debug("Begin PrePare Insert");
		this.formEdit = CepPersonRequest.builder().build();
		this.formEditSpouse = CepSpouseRequest.builder().checkDetail1("true").checkDetail2("true").build();
		this.formEditTuition = CepTuitionRequest.builder().build();
		this.spoList = new ArrayList<>();
		this.formEdit.setSpouseList(new ArrayList<>());
		this.tuiList = new ArrayList<>();
		this.formEdit.setTuitionList(new ArrayList<>());
		this.kidList = new ArrayList<>();
		this.formEdit.setKidList(new ArrayList<>());
		log.debug("End PrePare Insert");
	}

	public void setTable() {
		log.debug("Begin setTable");

		this.kidList.add(CepKidRequest.builder().kidUUID(UUID.randomUUID().toString()).build());

		log.debug("End setTable");
	}

	public void removeDataList(int index) {
		log.debug("Begin removeDataList...");
		this.kidList.remove(index);
		log.debug("End removeDataList...");
	}

	public void prepareData() {
		log.debug("Begin PrepareData...");
		try {
			if (this.mode.equals(MODE_INSERT)) {
				this.formEdit.setPerUUID(UUID.randomUUID().toString());
				this.formEditSpouse.setSpouseUUID(UUID.randomUUID().toString());
				this.spoList.add(formEditSpouse);
				this.formEditTuition.setTuitionUUID(UUID.randomUUID().toString());
				this.tuiList.add(formEditTuition);

			}

		} catch (Exception e) {
			log.error("Exception prepareData", e);
		} finally {
			log.debug("End prepareData..");
		}
	}

	public void checkGovEmp() {// สำหรับ ไม่เป็นอะไร
		log.debug("change value");
		String check = this.formEditSpouse.getCheckGovEmp();//ไม่เป็นs
		if(!check.isEmpty()) {
			this.formEditSpouse.setGov(null);//ข้าราชการ
			this.formEditSpouse.setEmp(null);//ลูกจ้าง
			this.formEditSpouse.setEntGov(null);//พนักงาน
			this.formEditSpouse.setOrgBkk(null);//กทม
			this.formEditSpouse.setCheckDetail1("true");
			this.formEditSpouse.setCheckDetail2("true");
			this.formEditSpouse.setPos("");
			this.formEditSpouse.setAffiliationn("");
			this.formEditSpouse.setPosSub("");
			this.formEditSpouse.setAffiliationnSub("");
		}
		
	}
	public void checkGov() {
		String govCheck = this.formEditSpouse.getGov();
		if(!govCheck.isEmpty()) {
			this.formEditSpouse.setCheckGovEmp(null);
			this.formEditSpouse.setEmp(null);
			this.formEditSpouse.setEntGov(null);
			this.formEditSpouse.setOrgBkk(null);
			this.formEditSpouse.setCheckDetail1("false");
			this.formEditSpouse.setCheckDetail2("true");
		}
	}
	public void checkEmp() {
		String empCheck = this.formEditSpouse.getEmp();
		if(!empCheck.isEmpty()) {
			this.formEditSpouse.setCheckGovEmp(null);
			this.formEditSpouse.setGov(null);
			this.formEditSpouse.setEntGov(null);
			this.formEditSpouse.setOrgBkk(null);
			this.formEditSpouse.setCheckDetail1("false");
			this.formEditSpouse.setCheckDetail2("true");
		}
	}
	public void entCheck() {
		String entCheck = this.formEditSpouse.getEntGov();
		if(!entCheck.isEmpty()) {
			this.formEditSpouse.setCheckGovEmp(null);
			this.formEditSpouse.setGov(null);
			this.formEditSpouse.setEmp(null);
			this.formEditSpouse.setOrgBkk(null);
			this.formEditSpouse.setCheckDetail1("true");
			this.formEditSpouse.setCheckDetail2("true");
			this.formEditSpouse.setPos("");
			this.formEditSpouse.setAffiliationn("");
			this.formEditSpouse.setPosSub("");
			this.formEditSpouse.setAffiliationnSub("");
		}
	}
	public void orgCheck() {
		String orgCheck = this.formEditSpouse.getOrgBkk();
		if(!orgCheck.isEmpty()) {
			this.formEditSpouse.setCheckGovEmp(null);
			this.formEditSpouse.setGov(null);
			this.formEditSpouse.setEmp(null);
			this.formEditSpouse.setEntGov(null);
			this.formEditSpouse.setCheckDetail1("true");
			this.formEditSpouse.setCheckDetail2("false");
		}
	}
	

	public void insertButton() {
		log.debug("Inserting...");
		CepPersonResponse res = null;

		try {

			this.prepareData();
			if (!this.tuiList.isEmpty()) {
				this.formEdit.getTuitionList().addAll(this.tuiList);
			}
			if (!this.spoList.isEmpty()) {
				this.formEdit.getSpouseList().addAll(this.spoList);
			}
			if (!this.kidList.isEmpty()) {
				this.formEdit.getKidList().addAll(this.kidList);
			}
			res = this.cepPersonManager.insert(this.formEdit);
			if (res.getStatus().equals(SimpleStatus.SUCCESS)) {
				this.onPageSearch();
				XspUtils.scrollToTop();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "", MassageComplete.INSERT_COMPLETE));

			} else {
				XspUtils.scrollToTop();
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", MassageError.INSERT_FAIL));
				throw new DefaultException(MassageError.INSERT_FAIL);
			}

		} catch (DefaultException de) {
			log.error("DefaultException insertButton {}", de);
		} catch (Exception e) {
			log.error("Exception insertButton {}", e);
		} finally {
			log.debug("Inserted");
		}
	}

	public void searchButton() {
		log.debug("Begin searchButtonOnclick");
		CepPersonResponse res = null;

		this.qList = new ArrayList<>();
		try {
			res = this.cepPersonManager.search(formCriteria);

			if (!res.getDataRequestList().isEmpty()) {

				this.qList.addAll(res.getDataRequestList());
			}

		} catch (Exception e) {
			log.debug("Exception searchButtonOnclick {}", e);

		}

	}

	public void prepareForUpdate() {
		log.debug("Begin searchForUpdate");
		CepPersonResponse res = null;
		try {
			res = this.cepPersonManager.findByReq(formSelected);
			if (!res.getDataRequestList().isEmpty()) {
				this.formEdit = res.getDataRequestList().get(0);

			}
			// หาคู่สมรส
			CepSpouseRequest reqSpo = CepSpouseRequest.builder().perUUID(this.formEdit.getPerUUID()).build();
			CepSpouseResponse resSpo = null;
			resSpo = this.cepSpouseManager.findByReq(reqSpo);
			this.formEdit.setSpouseList(resSpo.getDataRequestList());
			if (resSpo != null && !resSpo.getDataRequestList().isEmpty()) {
				this.spoList = resSpo.getDataRequestList();
				this.formEditSpouse = this.spoList.get(0);

			} else {
				this.spoList = new ArrayList<>();
			}
			// หารายละเอียดเบิก
			CepTuitionRequest reqTui = CepTuitionRequest.builder().perUUID(this.formEdit.getPerUUID()).build();
			CepTuitionResponse resTui = null;
			resTui = this.cepTuitionManager.findByReq(reqTui);
			this.formEdit.setTuitionList(resTui.getDataRequestList());
			if (resTui != null && !resTui.getDataRequestList().isEmpty()) {
				this.tuiList = resTui.getDataRequestList();
				this.formEditTuition = this.tuiList.get(0);
			} else {
				this.spoList = new ArrayList<>();
			}
			// หาตารางบุตร
			CepKidRequest reqKid = CepKidRequest.builder().perUUID(this.formEdit.getPerUUID()).build();
			CepKidResponse resKid = null;
			resKid = this.cepKidManager.findByReq(reqKid);
			this.formEdit.setKidList(resKid.getDataRequestList());
			if (resKid != null && !resKid.getDataRequestList().isEmpty()) {
				this.kidList = resKid.getDataRequestList();

			} else {
				this.spoList = new ArrayList<>();
			}

		} catch (Exception e) {
			log.debug("Exception searchButtonOnclick {}", e);
		}
	}

	public void onRowSelect(SelectEvent event) {
		log.debug("Begin onRowSelect...");
		try {
			this.mode = MODE_UPDATE;
			this.formEdit = CepPersonRequest.builder().build();
			this.prepareForUpdate();

		} catch (Exception e) {
			log.error("Error : {}", e);
		} finally {
			log.debug("End onRowSelect...");
		}

	}

	public void updateButton() {
		log.debug("Begin update ButtonOnClick ");
		CepPersonResponse cepRes = null;

		try {
			this.prepareData();
			if (!this.tuiList.isEmpty()) {
				this.formEdit.setTuitionList(new ArrayList<>());
				this.formEdit.getTuitionList().addAll(this.tuiList);
			}
			if (!this.spoList.isEmpty()) {
				this.formEdit.setSpouseList(new ArrayList<>());
				this.formEdit.getSpouseList().addAll(this.spoList);
			}
			if (!this.kidList.isEmpty()) {
				this.formEdit.setKidList(new ArrayList<>());
				this.formEdit.getKidList().addAll(this.kidList);
			}
			
			cepRes = this.cepPersonManager.update(this.formEdit);
			if (cepRes.getStatus().equals("SUCCESS")) {

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
			cepRes = null;
		}

	}
	public void deleteButton() {
		log.debug("Begin deletButtonOnClick...");
		
		CepPersonResponse resPer = null;

		try {
			resPer = this.cepPersonManager.delete(this.formEdit);

			if (resPer.getStatus().equals("SUCCESS")) {
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
			resPer = null;
		}

	}


}
