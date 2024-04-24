package com.summitthai.cr.apprentice.view;

import java.io.Serializable;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.summitthai.cr.apprentice.cep.person.manager.CepPersonManager;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonRequest;
import com.summitthai.cr.apprentice.cep.person.model.CepPersonResponse;
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

	@Inject
	private CepPersonManager cepPersonManager;

	public Cep02001Bean() {
		this.formEdit = CepPersonRequest.builder().build();

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
		log.debug("End OnPageSearch");
	}

	public void onPageInsert() {
		log.debug("Begin OnPageInsert");
		this.mode = MODE_INSERT;
		this.formEdit = CepPersonRequest.builder().checkGovEmp("false").gov("false").emp("false").entGov("false")
				.orgBkk("false").build();
		log.debug("End OnPageInsert");
	}

	public void preparePageInsert() {
		log.debug("Begin PrePare Insert");
		this.formEdit = CepPersonRequest.builder().build();
		log.debug("End PrePare Insert");
	}

	public void prepareData() {
		log.debug("Begin PrepareData...");
		try {
			if (this.mode.equals(MODE_INSERT)) {
				this.formEdit.setPerUUID(UUID.randomUUID().toString());
			}

		} catch (Exception e) {
			log.error("Exception prepareData", e);
		} finally {
			log.debug("End prepareData..");
		}
	}

	public void changeValue() {
		String checkGovEmp = this.formEdit.getCheckGovEmp();
		String gov = this.formEdit.getGov();
		String emp = this.formEdit.getEmp();
		String entGov = this.formEdit.getEntGov();
		String orgBkk = this.formEdit.getOrgBkk();

		if (!checkGovEmp.equals("false")) {
			this.formEdit.setCheckGovEmp("true");
			this.formEdit.setGov("false");
			this.formEdit.setEmp("false");
			this.formEdit.setEntGov("false");
			this.formEdit.setOrgBkk("false");
		} else if (!gov.equals("false")) {
			this.formEdit.setCheckGovEmp("false");
			this.formEdit.setGov("true");
			this.formEdit.setEmp("false");
			this.formEdit.setEntGov("false");
			this.formEdit.setOrgBkk("false");
		} else if (!emp.equals("false")) {
			this.formEdit.setCheckGovEmp("false");
			this.formEdit.setGov("false");
			this.formEdit.setEmp("true");
			this.formEdit.setEntGov("false");
			this.formEdit.setOrgBkk("false");
		} else if (!entGov.equals("false")) {
			this.formEdit.setCheckGovEmp("false");
			this.formEdit.setGov("false");
			this.formEdit.setEmp("false");
			this.formEdit.setEntGov("true");
			this.formEdit.setOrgBkk("false");
		} else if (!orgBkk.equals("false")) {
			this.formEdit.setCheckGovEmp("false");
			this.formEdit.setGov("false");
			this.formEdit.setEmp("false");
			this.formEdit.setEntGov("false");
			this.formEdit.setOrgBkk("true");
		}
	}

	public void insertButton() {
		log.debug("Inserting...");
		CepPersonResponse res = null;

		try {

			this.prepareData();

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

}
