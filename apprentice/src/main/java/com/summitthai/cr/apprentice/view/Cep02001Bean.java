package com.summitthai.cr.apprentice.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.summitthai.sdd.faces.view.base.ViewBase;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Named
@ViewScoped
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class Cep02001Bean extends ViewBase implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public Cep02001Bean() {
		
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
		log.debug("End OnPageInsert");
	}
}
