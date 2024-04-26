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

import com.summitthai.cr.apprentice.cep.kid.model.CepKidRequest;
import com.summitthai.cr.apprentice.cep.person.manager.CepPersonManager;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonRequest;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonResponse;
import com.summitthai.cr.apprentice.cep.spouse.model.CepSpouseRequest;
import com.summitthai.cr.apprentice.cep.tuition.model.CepTuitionRequest;
import com.summitthai.cr.apprentice.deptH.model.HolidayDeptRequest;
import com.summitthai.cr.apprentice.exception.DefaultException;
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
public class Cep02001Bean extends ViewBase implements Serializable {
	private static final long serialVersionUID = 1L;

	private CepPersonRequest formEdit;
	private CepPersonRequest formCriteria;
	private CepSpouseRequest formEditSpouse;
	private CepTuitionRequest formEditTuition;

	@Inject
	private CepPersonManager cepPersonManager;

	private List<CepTuitionRequest> tuiList;
	private List<CepSpouseRequest> spoList;
	private List<CepKidRequest> kidList;

	private List<CepPersonRequest> qList;

	public Cep02001Bean() {
		this.formCriteria = CepPersonRequest.builder().build();
		this.formEdit = CepPersonRequest.builder().build();
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

	public void changeValue() {
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

}
